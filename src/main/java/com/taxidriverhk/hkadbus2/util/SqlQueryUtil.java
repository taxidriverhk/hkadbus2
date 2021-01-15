package com.taxidriverhk.hkadbus2.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SqlQueryUtil {

    public static <T> List<T> selectAll(
            final Session session,
            final Class<T> entityClass
    ) {
        final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        final CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entityClass);
        return session.createQuery(criteriaQuery.select(criteriaQuery.from(entityClass))).getResultList();
    }

    public static <T,V> Optional<T> selectSingleItemByCompositeKey(
            final Session session,
            final Class<T> entityClass,
            final String attribute,
            final V value
    ) {
        return selectMatchingItemsByCompositeKey(
                session,
                entityClass,
                attribute,
                value).stream().findFirst();
    }

    public static <T,V> List<T> selectMatchingItemsByCompositeKey(
            final Session session,
            final Class<T> entityClass,
            final String attribute,
            final V value
    ) {
        return createSelectQueryBySingleAttribute(
                session,
                entityClass,
                root -> root.get(attribute),
                value).getResultList();
    }

    public static <T,V> List<T> selectMatchingItemsByForeignKey(
            final Session session,
            final Class<T> entityClass,
            final String foreignKeyAttribute,
            final String referencedAttribute,
            final V value
    ) {
        return createSelectQueryBySingleAttribute(
                session,
                entityClass,
                root -> root.get(foreignKeyAttribute).get(referencedAttribute),
                value).getResultList();
    }

    private static <T,V> Query<T> createSelectQueryBySingleAttribute(
            final Session session,
            final Class<T> entityClass,
            final Function<Root<T>, Path<T>> attributeFunc,
            final V value
    ) {
        final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        final CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entityClass);
        final Root<T> root = criteriaQuery.from(entityClass);
        return session.createQuery(criteriaQuery
                .select(root)
                .where(criteriaBuilder
                        .equal(attributeFunc.apply(root), value)));
    }
}
