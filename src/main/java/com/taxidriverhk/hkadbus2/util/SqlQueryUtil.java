package com.taxidriverhk.hkadbus2.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.taxidriverhk.hkadbus2.exception.InternalErrorException;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

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

    // This assumes that a transaction has been initiated before calling this function
    public static <T> T insertEntity(
            final Session session,
            final Class<T> entityClass,
            final T entity
    ) {
        final UUID id = (UUID) session.save(entity);
        try {
            final Method setIdMethod = entityClass.getMethod("setId", UUID.class);
            setIdMethod.invoke(entity, id);
            return entity;
        } catch (final NoSuchMethodException | IllegalAccessException | InvocationTargetException ex) {
            throw new InternalErrorException(String.format("Unable to insert entity into database: %s", ex.getMessage()));
        }
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
