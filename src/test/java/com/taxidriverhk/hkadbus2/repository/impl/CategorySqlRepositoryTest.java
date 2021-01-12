package com.taxidriverhk.hkadbus2.repository.impl;

import com.taxidriverhk.hkadbus2.model.entity.CategoryEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.taxidriverhk.hkadbus2.util.MockDataHelper.CATEGORY_ENTITY;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

public class CategorySqlRepositoryTest extends SqlRepositoryTestBase {

    private CategorySqlRepository sqlRepository;

    @BeforeEach
    public void setup() {
        sqlRepository = new CategorySqlRepository(sessionFactory);
    }

    @Test
    public void WHEN_queryWithoutFilter_THEN_shouldReturnAllCategories() {
        final List<CategoryEntity> categoryEntities = sqlRepository.getCategories();
        assertThat(categoryEntities, containsInAnyOrder(CATEGORY_ENTITY));
    }

    @Override
    protected void setupDataForTest(Session session) {
        final Transaction transaction = session.beginTransaction();
        session.save(CATEGORY_ENTITY);
        transaction.commit();
    }
}
