package com.taxidriverhk.hkadbus2.provider.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.taxidriverhk.hkadbus2.model.domain.EntityOptionType;
import com.taxidriverhk.hkadbus2.model.entity.SearchRecordEntity;
import com.taxidriverhk.hkadbus2.provider.EntityOptionsProvider;

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

        final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        final CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createTupleQuery();
        final Root<SearchRecordEntity> root = criteriaQuery.from(SearchRecordEntity.class);

        final String hashKey = ENTITY_ATTRIBUTE_MAPPING.get(entityType).get(0);
        final String name = ENTITY_ATTRIBUTE_MAPPING.get(entityType).get(1);

        final Predicate languagePredicate = criteriaBuilder.equal(root.get("language"), language);
        final CriteriaQuery<Tuple> criteriaQueryWithPredicate = criteriaQuery
                .multiselect(
                        root.get(hashKey),
                        root.get(name))
                .where(languagePredicate)
                .orderBy(criteriaBuilder.asc(root.get(name)))
                .distinct(true);

        final Query<Tuple> selectionQuery = session.createQuery(criteriaQueryWithPredicate);
        final List<Tuple> tupleResults = selectionQuery.getResultList();

        return tupleResults.stream()
                .collect(Collectors.toMap(
                        tuple -> tuple.get(0).toString(),
                        tuple -> tuple.get(1).toString()
                ));
    }
}
