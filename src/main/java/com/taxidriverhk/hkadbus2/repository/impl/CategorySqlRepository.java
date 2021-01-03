package com.taxidriverhk.hkadbus2.repository.impl;

import com.taxidriverhk.hkadbus2.model.entity.CategoryEntity;
import com.taxidriverhk.hkadbus2.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class CategorySqlRepository implements CategoryRepository {

    private final SessionFactory sessionFactory;

    @Override
    public List<CategoryEntity> getCategories() {
        final Session session = sessionFactory.openSession();
        final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        final CriteriaQuery<CategoryEntity> criteriaQuery = criteriaBuilder.createQuery(CategoryEntity.class);
        final List<CategoryEntity> results = session.createQuery(criteriaQuery.select(criteriaQuery.from(CategoryEntity.class))).getResultList();
        session.close();
        return results;
    }

    @Override
    public Optional<CategoryEntity> getCategoryByHashKey(String hashKey) {
        return Optional.empty();
    }
}
