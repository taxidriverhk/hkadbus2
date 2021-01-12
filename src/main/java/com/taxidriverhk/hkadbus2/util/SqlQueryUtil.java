package com.taxidriverhk.hkadbus2.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

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
}
