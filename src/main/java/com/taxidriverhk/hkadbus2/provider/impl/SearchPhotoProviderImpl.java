package com.taxidriverhk.hkadbus2.provider.impl;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.hibernate.query.criteria.JpaCriteriaQuery;
import org.hibernate.query.criteria.JpaExpression;
import org.hibernate.query.criteria.JpaOrder;
import org.hibernate.query.criteria.JpaPredicate;
import org.hibernate.query.criteria.JpaRoot;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.taxidriverhk.hkadbus2.exception.BadRequestException;
import com.taxidriverhk.hkadbus2.mapper.EntityMapper;
import com.taxidriverhk.hkadbus2.model.domain.SearchPhotoFilter;
import com.taxidriverhk.hkadbus2.model.domain.SearchRecord;
import com.taxidriverhk.hkadbus2.model.domain.SortDirection;
import com.taxidriverhk.hkadbus2.model.entity.SearchRecordEntity;
import com.taxidriverhk.hkadbus2.model.entity.result.SearchRecordResult;
import com.taxidriverhk.hkadbus2.provider.SearchPhotoProvider;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Log4j2
public class SearchPhotoProviderImpl implements SearchPhotoProvider {

    private static final String NEXT_PAGE_CURSOR_SEPARATOR = "-";
    private static final String NEXT_PAGE_CURSOR_FORMAT = "%s" + NEXT_PAGE_CURSOR_SEPARATOR + "%s";
    private static final String PHOTO_ID_ATTRIBUTE_NAME = "photoShortId";
    private static final String TAGS_ATTRIBUTE_NAME = "tags";
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
            .put("fleetNumber", SearchPhotoFilter::getFleetNumbers)
            .put("licensePlateNumber", SearchPhotoFilter::getLicensePlateNumbers)
            .put("username", SearchPhotoFilter::getUploaderNames)
            .put("thumbnail", SearchPhotoFilter::getThumbnails)
            .put("language", filter -> Strings.isNullOrEmpty(filter.getLanguage())
                    ? Collections.emptyList()
                    : Collections.singletonList(filter.getLanguage()))
            .build();

    private final SessionFactory sessionFactory;

    public SearchRecordResult searchPhotos(
            final List<String> queryTexts,
            final String orderBy,
            final String sort,
            final SearchPhotoFilter filter,
            final String nextPageCursor,
            final int limit
    ) {
        final Session session = sessionFactory.openSession();
        final HibernateCriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

        log.info("Building search query using query texts {} filter {}", queryTexts, filter);
        final JpaCriteriaQuery<Long> countCriteriaQuery = criteriaBuilder.createQuery(Long.class);
        final JpaRoot<SearchRecordEntity> countRoot = countCriteriaQuery.from(SearchRecordEntity.class);
        final JpaExpression<Long> countExpression = criteriaBuilder.count(countRoot);
        final List<JpaPredicate> countFilterPredicates = buildSelectQueryFromFilter(criteriaBuilder, countRoot, queryTexts, filter);
        final Query countHitsQuery = session.createQuery(countCriteriaQuery
                .select(countExpression)
                .where(countFilterPredicates.toArray(new JpaPredicate[0])));
        log.info("Getting total hits of the query");
        final Long total = (Long) countHitsQuery.getSingleResult();
        log.info("Total hits of of the query: {}", total);

        log.info("The query will be ordered by {}", orderBy);
        final JpaCriteriaQuery<SearchRecordEntity> criteriaQuery = criteriaBuilder.createQuery(SearchRecordEntity.class);
        final JpaRoot<SearchRecordEntity> root = criteriaQuery.from(SearchRecordEntity.class);
        final List<JpaOrder> orders = buildOrderByQuery(criteriaBuilder, root, orderBy, sort);

        final List<JpaPredicate> filterPredicates = buildSelectQueryFromFilter(criteriaBuilder, root, queryTexts, filter);
        if (!Strings.isNullOrEmpty(nextPageCursor)) {
            final String[] nextPageCursorTokens = nextPageCursor.split(NEXT_PAGE_CURSOR_SEPARATOR);
            if (nextPageCursorTokens.length != 2) {
                throw new BadRequestException(String.format("Invalid next page cursor %s", nextPageCursor));
            }

            log.info("Next page cursor is defined as {}", nextPageCursor);
            final String lastSortKey = nextPageCursorTokens[0];
            final String lastId = nextPageCursorTokens[1];
            filterPredicates.add(criteriaBuilder.or(
                    buildSortKeyQuery(criteriaBuilder, root, lastSortKey, orderBy, sort),
                    buildTieBreakerQuery(criteriaBuilder, root, lastSortKey, orderBy, lastId)));
        }

        log.info("Getting the actual result set of the query");
        final Query searchQuery = session.createQuery(criteriaQuery
                .select(root)
                .where(filterPredicates.toArray(new JpaPredicate[0]))
                .orderBy(orders.toArray(new JpaOrder[0])));
        final List<SearchRecordEntity> searchRecordEntities = searchQuery.getResultList();
        final List<SearchRecordEntity> searchRecordEntitiesWithLimit = searchRecordEntities.stream()
                .limit(limit)
                .collect(Collectors.toList());

        session.close();

        final Optional<String> nextPageCursorOptional = getNextPageCursor(searchRecordEntitiesWithLimit, limit, total, orderBy);
        log.info("Returning {} actual search record entities with next page cursor {}", searchRecordEntitiesWithLimit.size(), nextPageCursorOptional);

        return SearchRecordResult.builder()
                .total(total)
                .searchRecordEntities(searchRecordEntitiesWithLimit)
                .nextPageCursor(nextPageCursorOptional.orElse(null))
                .build();
    }

    public void insertSearchRecord(final SearchRecord searchRecord, final String language) {
        final Session session = sessionFactory.openSession();
        final Transaction transaction = session.beginTransaction();

        final SearchRecordEntity entity = EntityMapper.INSTANCE.searchRecordToSearchRecordEntity(searchRecord);
        entity.setLanguage(language);

        session.save(entity);
        transaction.commit();
        session.close();
    }

    private List<JpaOrder> buildOrderByQuery(
            final HibernateCriteriaBuilder criteriaBuilder,
            final JpaRoot<SearchRecordEntity> root,
            final String orderBy,
            final String sort
    ) {
        return Lists.newArrayList(
                SortDirection.ASC.getName().equals(sort)
                        ? criteriaBuilder.asc(root.get(orderBy))
                        : criteriaBuilder.desc(root.get(orderBy)),
                criteriaBuilder.asc(root.get(PHOTO_ID_ATTRIBUTE_NAME)));
    }

    private List<JpaPredicate> buildSelectQueryFromFilter(
            final HibernateCriteriaBuilder criteriaBuilder,
            final JpaRoot<SearchRecordEntity> root,
            final List<String> queryTexts,
            final SearchPhotoFilter filter
    ) {
        final List<JpaPredicate> filterPredicates = FIELD_FILTER_MAPPING.entrySet()
                .stream()
                .filter(entry -> CollectionUtils.isNotEmpty(entry.getValue().apply(filter)))
                .map(entry -> criteriaBuilder.lower(root.get(entry.getKey()))
                        .in(entry.getValue().apply(filter)
                                .stream()
                                .map(String::toLowerCase)
                                .collect(Collectors.toList())))
                .collect(Collectors.toList());

        if (CollectionUtils.isNotEmpty(queryTexts)) {
            final List<JpaPredicate> queryTextPredicates = queryTexts.stream()
                    .map(queryText -> criteriaBuilder.like(
                            criteriaBuilder.lower(root.get(TAGS_ATTRIBUTE_NAME)), "%" + queryText.toLowerCase() + "%"))
                    .collect(Collectors.toList());
            final JpaPredicate combinedQueryTextPredicate = criteriaBuilder.or(queryTextPredicates.toArray(new JpaPredicate[0]));
            filterPredicates.add(combinedQueryTextPredicate);
        }

        return filterPredicates;
    }

    private JpaPredicate buildSortKeyQuery(
            final HibernateCriteriaBuilder criteriaBuilder,
            final JpaRoot<SearchRecordEntity> root,
            final String lastSortKey,
            final String orderBy,
            final String sort
    ) {
        return SortDirection.ASC.getName().equals(sort)
                ? criteriaBuilder.greaterThan(root.get(orderBy), lastSortKey)
                : criteriaBuilder.lessThan(root.get(orderBy), lastSortKey);
    }

    private JpaPredicate buildTieBreakerQuery(
            final HibernateCriteriaBuilder criteriaBuilder,
            final JpaRoot<SearchRecordEntity> root,
            final String lastSortKey,
            final String orderBy,
            final String lastIdStr
    ) {
        final Long lastId = Long.valueOf(lastIdStr);
        return criteriaBuilder.and(
                criteriaBuilder.greaterThan(root.get(PHOTO_ID_ATTRIBUTE_NAME), lastId),
                criteriaBuilder.equal(root.get(orderBy), lastSortKey));
    }

    private Optional<String> getNextPageCursor(
            final List<SearchRecordEntity> searchRecordEntities,
            final int limit,
            final Long total,
            final String orderBy
    ) {
        if (searchRecordEntities.size() < 1 ||
            searchRecordEntities.size() < limit ||
            total <= searchRecordEntities.size()) {
            return Optional.empty();
        }

        try {
            final SearchRecordEntity lastSearchRecord = searchRecordEntities.get(searchRecordEntities.size() - 1);
            final Object lastSortKey = new PropertyDescriptor(orderBy, SearchRecordEntity.class).getReadMethod().invoke(lastSearchRecord);
            final Long lastId = lastSearchRecord.getPhotoShortId();
            return Optional.of(String.format(NEXT_PAGE_CURSOR_FORMAT, lastSortKey, lastId));
        } catch (final IntrospectionException | IllegalAccessException | InvocationTargetException ex) {
            throw new BadRequestException(String.format("Invalid order by field %s", orderBy));
        }
    }
}
