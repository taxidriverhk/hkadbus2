package com.taxidriverhk.hkadbus2.provider;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableMap;
import com.taxidriverhk.hkadbus2.model.domain.SearchPhotoFilter;
import com.taxidriverhk.hkadbus2.model.domain.SortDirection;
import com.taxidriverhk.hkadbus2.model.entity.SearchRecordEntity;
import com.taxidriverhk.hkadbus2.model.entity.result.SearchRecordResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Log4j2
public class SearchPhotoProvider {

    private static final Map<String, Function<SearchPhotoFilter, List<String>>> FIELD_FILTER_MAPPING = ImmutableMap
            .<String, Function<SearchPhotoFilter, List<String>>>builder()
            .put("advertisementName", SearchPhotoFilter::getAdvertisementNames)
            .put("advertisementHashKey", SearchPhotoFilter::getAdvertisementIds)
            .put("busCompanyName", SearchPhotoFilter::getBusCompanyNames)
            .put("busModelName", SearchPhotoFilter::getBusModelNames)
            .put("busModelHashKey", SearchPhotoFilter::getBusModelIds)
            .put("categoryName", SearchPhotoFilter::getCategoryNames)
            .put("categoryHashKey", SearchPhotoFilter::getCategoryIds)
            .put("routeHashKey", SearchPhotoFilter::getBusRouteIds)
            .put("routeNumber", SearchPhotoFilter::getBusRouteNumbers)
            .put("fleetPrefix", SearchPhotoFilter::getFleetPrefixes)
            .put("licensePlateNumber", SearchPhotoFilter::getLicensePlateNumbers)
            .put("username", SearchPhotoFilter::getUploaderNames)
            .put("language", filter -> Strings.isNullOrEmpty(filter.getLanguage())
                    ? Collections.EMPTY_LIST
                    : Collections.singletonList(filter.getLanguage()))
            .build();

    private final SessionFactory sessionFactory;

    public SearchRecordResult searchPhotos(
            final List<String> queryTexts,
            final String orderBy,
            final String sort,
            final SearchPhotoFilter filter,
            final String nextSortKey,
            final int limit
    ) {
        final Session session = sessionFactory.openSession();

        final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        final CriteriaQuery<SearchRecordEntity> criteriaQuery = criteriaBuilder.createQuery(SearchRecordEntity.class);
        final Root<SearchRecordEntity> root = criteriaQuery.from(SearchRecordEntity.class);

        log.info("Building search query using query texts {} filter {}", queryTexts, filter);
        final List<Predicate> filterPredicates = buildSelectQueryFromFilter(criteriaBuilder, root, queryTexts, filter);
        log.info("The query will be ordered by {} with sort key {}", orderBy, nextSortKey);
        final Order order = buildOrderByQuery(criteriaBuilder, root, orderBy, sort);
        final Query searchQueryWithoutSortKey = session.createQuery(criteriaQuery
                .select(root)
                .where(filterPredicates.toArray(new Predicate[0]))
                .orderBy(order));
        log.info("Getting total hits of the query");
        final Long total = searchQueryWithoutSortKey.getResultStream().count();
        log.info("Total hits of of the query: {}", total);

        final Optional<Predicate> sortKeyQuery = buildSortKeyQuery(criteriaBuilder, root, nextSortKey, orderBy, sort);
        if (sortKeyQuery.isPresent()) {
            filterPredicates.add(sortKeyQuery.get());
        }
        log.info("Getting the actual result set of the query");
        final Query searchQuery = session.createQuery(criteriaQuery
                .select(root)
                .where(filterPredicates.toArray(new Predicate[0]))
                .orderBy(order));
        final List<SearchRecordEntity> searchRecordEntities = searchQuery.getResultList();
        final List<SearchRecordEntity> searchRecordEntitiesWithLimit = searchRecordEntities.stream()
                .limit(limit)
                .collect(Collectors.toList());
        log.info("Returning {} actual search record entities", searchRecordEntitiesWithLimit.size());

        session.close();

        return SearchRecordResult.builder()
                .total(total)
                .searchRecordEntities(searchRecordEntitiesWithLimit)
                .build();
    }

    private Order buildOrderByQuery(
            final CriteriaBuilder criteriaBuilder,
            final Root<SearchRecordEntity> root,
            final String orderBy,
            final String sort
    ) {
        return SortDirection.ASC.getName().equals(sort)
                ? criteriaBuilder.asc(root.get(orderBy))
                : criteriaBuilder.desc(root.get(orderBy));
    }

    private List<Predicate> buildSelectQueryFromFilter(
            final CriteriaBuilder criteriaBuilder,
            final Root<SearchRecordEntity> root,
            final List<String> queryTexts,
            final SearchPhotoFilter filter
    ) {
        final List<Predicate> filterPredicates = FIELD_FILTER_MAPPING.entrySet()
                .stream()
                .filter(entry -> CollectionUtils.isNotEmpty(entry.getValue().apply(filter)))
                .map(entry -> criteriaBuilder.lower(root.get(entry.getKey()))
                        .in(entry.getValue().apply(filter)
                                .stream()
                                .map(String::toLowerCase)
                                .collect(Collectors.toList())))
                .collect(Collectors.toList());

        if (CollectionUtils.isNotEmpty(queryTexts)) {
            final List<Predicate> queryTextPredicates = queryTexts.stream()
                    .map(queryText -> criteriaBuilder.like(
                            criteriaBuilder.lower(root.get("tags")), "%" + queryText.toLowerCase() + "%"))
                    .collect(Collectors.toList());
            final Predicate combinedQueryTextPredicate = criteriaBuilder.or(queryTextPredicates.toArray(new Predicate[0]));
            filterPredicates.add(combinedQueryTextPredicate);
        }

        return filterPredicates;
    }

    private Optional<Predicate> buildSortKeyQuery(
            final CriteriaBuilder criteriaBuilder,
            final Root<SearchRecordEntity> root,
            final String nextSortKey,
            final String orderBy,
            final String sort
    ) {
        if (!Strings.isNullOrEmpty(nextSortKey)) {
            return Optional.of(SortDirection.ASC.getName().equals(sort)
                    ? criteriaBuilder.greaterThan(root.get(orderBy), nextSortKey)
                    : criteriaBuilder.lessThan(root.get(orderBy), nextSortKey));
        }
        return Optional.empty();
    }
}
