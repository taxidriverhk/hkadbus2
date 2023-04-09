package com.taxidriverhk.hkadbus2.repository.impl;

import static com.taxidriverhk.hkadbus2.util.MockDataHelper.CATEGORY_ENTITY_1;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.CATEGORY_HASH_KEY_1;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.LANGUAGE_EN;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.LANGUAGE_ZH;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.google.common.collect.ImmutableMap;
import com.taxidriverhk.hkadbus2.model.entity.CategoryEntity;

public class CategorySqlRepositoryTest extends SqlRepositoryTestBase {

    private CategorySqlRepository sqlRepository;

    @BeforeEach
    public void setup() {
        sqlRepository = new CategorySqlRepository(sessionFactory);
    }

    @Test
    public void WHEN_queryWithoutFilter_THEN_shouldReturnAllCategories() {
        final List<CategoryEntity> categoryEntities = sqlRepository.getCategories();
        assertThat(categoryEntities, containsInAnyOrder(CATEGORY_ENTITY_1));
    }

    @Test
    public void WHEN_queryByHashKey_THEN_shouldReturnMatchingCategory() {
        final Optional<CategoryEntity> categoryEntity = sqlRepository.getCategoryByHashKey(CATEGORY_HASH_KEY_1);
        assertThat(categoryEntity.get(), equalTo(CATEGORY_ENTITY_1));

        final Optional<CategoryEntity> nonExistentCategoryEntity = sqlRepository.getCategoryByHashKey("invalid-key");
        assertThat(nonExistentCategoryEntity.isPresent(), equalTo(false));
    }

    @Test
    public void WHEN_insertNewEntity_THEN_shouldReturnCategoryId() {
        final String categoryId = sqlRepository.putCategory(CategoryEntity.builder()
                .name(ImmutableMap.of(
                        LANGUAGE_EN, "Finance",
                        LANGUAGE_ZH, "金融"))
                .hashKey("finance")
                .build());
        assertThat(categoryId, notNullValue());

        final Optional<CategoryEntity> categoryEntity = sqlRepository.getCategoryByHashKey("finance");
        assertThat(categoryEntity.isPresent(), equalTo(true));
    }

    @Override
    protected void setupDataForTest(Session session) {
        final Transaction transaction = session.beginTransaction();
        session.save(CATEGORY_ENTITY_1);
        transaction.commit();
    }
}
