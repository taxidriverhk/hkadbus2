package com.taxidriverhk.hkadbus2.util;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Supplier;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.hibernate.query.criteria.JpaCriteriaQuery;
import org.hibernate.query.criteria.JpaPath;
import org.hibernate.query.criteria.JpaRoot;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SqlQueryUtil {

    public static <T> List<T> selectAll(
            final Session session,
            final Class<T> entityClass
    ) {
        final HibernateCriteriaBuilder  criteriaBuilder = session.getCriteriaBuilder();
        final JpaCriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entityClass);
        return session.createQuery(criteriaQuery.select(criteriaQuery.from(entityClass))).getResultList();
    }

    public static <T> Optional<T> selectSingleItemByPrimaryKey(
            final Session session,
            final Class<T> entityClass,
            final String id
    ) {
        final UUID key = UUID.fromString(id);
        final T entity = (T) session.get(entityClass, key);
        return Optional.ofNullable(entity);
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

    public static <T> T mutateWithTransaction(
            final Session session,
            final Class<T> entityClass,
            final Supplier<T> mutation
    ) {
        final Transaction transaction = session.beginTransaction();
        final T entity = mutation.get();
        transaction.commit();

        return entity;
    }

    // This assumes that a transaction has been initiated before calling this function
    public static <T> T insertEntity(
            final Session session,
            final Class<T> entityClass,
            final T entity
    ) {
        session.persist(entity);
        session.flush();
        return entity;
    }

    private static <T,V> Query<T> createSelectQueryBySingleAttribute(
            final Session session,
            final Class<T> entityClass,
            final Function<JpaRoot<T>, JpaPath<T>> attributeFunc,
            final V value
    ) {
        final HibernateCriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        final JpaCriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entityClass);
        final JpaRoot<T> root = criteriaQuery.from(entityClass);
        return session.createQuery(criteriaQuery
                .select(root)
                .where(criteriaBuilder
                        .equal(attributeFunc.apply(root), value)));
    }
}
