package com.taxidriverhk.hkadbus2.repository.impl;

import com.mongodb.client.MongoCollection;
import com.taxidriverhk.hkadbus2.model.entity.CategoryEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static com.taxidriverhk.hkadbus2.util.MockDataHelper.CATEGORY_ENTITY;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;

public class CategoryMongoDBRepositoryTest extends MongoDBRepositoryTestBase<CategoryEntity> {

    private CategoryMongoDBRepository categoryMongoDBRepository;

    public CategoryMongoDBRepositoryTest() {
        super(CategoryEntity.class, "categories");
    }

    @BeforeEach
    public void setup() {
        categoryMongoDBRepository = new CategoryMongoDBRepository(mongoDatabase);
    }

    @Test
    public void WHEN_getCategories_THEN_shouldFindAllCategoriesFromCollection() {
        final List<CategoryEntity> categoryEntities = categoryMongoDBRepository.getCategories();
        assertThat(categoryEntities, containsInAnyOrder(CATEGORY_ENTITY));
    }

    @Test
    public void WHEN_queryCategoryByValidHashKey_THEN_shouldReturnMatchingEntity() {
        assertThat(categoryMongoDBRepository.getCategoryByHashKey("food"), equalTo(Optional.of(CATEGORY_ENTITY)));
    }

    @Test
    public void WHEN_queryCategoryByInvalidHashKey_THEN_shouldReturnNothing() {
        assertThat(categoryMongoDBRepository.getCategoryByHashKey("invalid"), equalTo(Optional.empty()));
    }

    @Override
    protected void setupDataForTests(final MongoCollection<CategoryEntity> mongoCollection) {
        mongoCollection.insertOne(CATEGORY_ENTITY);
    }
}
