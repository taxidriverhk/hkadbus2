package com.taxidriverhk.hkadbus2.provider.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.hibernate.query.criteria.JpaCriteriaQuery;
import org.hibernate.query.criteria.JpaPredicate;
import org.hibernate.query.criteria.JpaRoot;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.taxidriverhk.hkadbus2.model.domain.EntityOptionType;
import com.taxidriverhk.hkadbus2.model.entity.BusRouteEntity;
import com.taxidriverhk.hkadbus2.model.entity.SearchRecordEntity;
import com.taxidriverhk.hkadbus2.provider.EntityOptionsProvider;

import jakarta.persistence.Tuple;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EntityOptionsProviderImpl implements EntityOptionsProvider {

    private static final Map<EntityOptionType, List<String>> ENTITY_ATTRIBUTE_MAPPING = ImmutableMap.<EntityOptionType, List<String>>builder()
            .put(EntityOptionType.ADVERTISEMENT, Lists.newArrayList("advertisementHashKey", "advertisementName"))
            .put(EntityOptionType.BUS_MODEL, Lists.newArrayList("busModelHashKey", "busModelName"))
            .put(EntityOptionType.CATEGORY, Lists.newArrayList("categoryHashKey", "categoryName"))
            .put(EntityOptionType.LICENSE_PLATE_NUMBER, Lists.newArrayList("licensePlateNumber", "licensePlateNumber"))
            .put(EntityOptionType.ROUTE, Lists.newArrayList("routeHashKey", "routeNumber"))
            .build();

    private final SessionFactory sessionFactory;

    @Override
    public Map<String, String> getEntityOptions(final EntityOptionType entityType, final String language) {
        final Session session = sessionFactory.openSession();
        if (entityType.equals(EntityOptionType.LOCATION)) {
            return getEntityOptionsForLocations(session, language);
        }

        final HibernateCriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        final JpaCriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createTupleQuery();
        final JpaRoot<SearchRecordEntity> root = criteriaQuery.from(SearchRecordEntity.class);

        final String hashKey = ENTITY_ATTRIBUTE_MAPPING.get(entityType).get(0);
        final String name = ENTITY_ATTRIBUTE_MAPPING.get(entityType).get(1);

        final JpaPredicate languagePredicate = criteriaBuilder.equal(root.get("language"), language);
        final JpaCriteriaQuery<Tuple> criteriaQueryWithPredicate = criteriaQuery
                .multiselect(
                        root.get(hashKey),
                        root.get(name))
                .where(languagePredicate)
                .orderBy(criteriaBuilder.asc(root.get(name)))
                .distinct(true);

        final Query<Tuple> selectionQuery = session.createQuery(criteriaQueryWithPredicate);
        final List<Tuple> tupleResults = selectionQuery.getResultList();

        session.close();

        return tupleResults.stream()
                .collect(Collectors.toMap(
                        tuple -> tuple.get(0).toString(),
                        tuple -> tuple.get(1).toString(),
                        (key1, key2) -> key1
                ));
    }

    private Map<String, String> getEntityOptionsForLocations(final Session session, final String langauge) {
        final HibernateCriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        final JpaCriteriaQuery<BusRouteEntity> criteriaQuery = criteriaBuilder.createQuery(BusRouteEntity.class);
        final JpaRoot<BusRouteEntity> root = criteriaQuery.from(BusRouteEntity.class);

        final JpaCriteriaQuery<BusRouteEntity> criteriaQueryWithColumns = criteriaQuery.select(root);
        final Query<BusRouteEntity> selectionQuery = session.createQuery(criteriaQueryWithColumns);
        final List<BusRouteEntity> entityResults = selectionQuery.getResultList();

        final Map<String, String> locationNames = entityResults.stream()
                .map((entityResult) -> Lists.newArrayList(
                        entityResult.getStartLocation(),
                        entityResult.getEndLocation()
                ))
                .flatMap(List::stream)
                // Always use the English location name as the key
                .collect(Collectors.toMap(
                        location -> location.get("en_us"),
                        location -> location.get(langauge),
                        (key1, key2) -> key1
                ));

        session.close();

        return locationNames;
    }
}
