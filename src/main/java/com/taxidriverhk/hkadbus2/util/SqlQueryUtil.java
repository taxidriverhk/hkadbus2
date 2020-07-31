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
            final Class<T> daoClass
    ) {
        final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        final CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(daoClass);
        return session.createQuery(criteriaQuery.select(criteriaQuery.from(daoClass))).getResultList();
    }

    public static <T,V> Optional<T> selectSingleItemByCompositeKey(
            final Session session,
            final Class<T> daoClass,
            final String attribute,
            final V value
    ) {
        return createSelectQueryBySingleAttribute(
                session,
                daoClass,
                root -> root.get(attribute),
                value)
                .getResultList()
                .stream()
                .findFirst();
    }

    public static <T,V> List<T> selectMatchingItemsByCompositeKey(
            final Session session,
            final Class<T> daoClass,
            final String attribute,
            final V value
    ) {
        return createSelectQueryBySingleAttribute(
                session,
                daoClass,
                root -> root.get(attribute),
                value).getResultList();
    }

    public static <T,V> List<T> selectMatchingItemsByForeignKey(
            final Session session,
            final Class<T> daoClass,
            final String foreignKeyAttribute,
            final String referencedAttribute,
            final V value
    ) {
        return createSelectQueryBySingleAttribute(
                session,
                daoClass,
                root -> root.get(foreignKeyAttribute).get(referencedAttribute),
                value).getResultList();
    }

    private static <T,V> Query<T> createSelectQueryBySingleAttribute(
            final Session session,
            final Class<T> daoClass,
            final Function<Root<T>, Path<T>> attributeFunc,
            final V value
    ) {
        final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        final CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(daoClass);
        final Root<T> root = criteriaQuery.from(daoClass);
        return session.createQuery(criteriaQuery
                .select(root)
                .where(criteriaBuilder.equal(attributeFunc.apply(root), value)));
    }
}
