package com.taxidriverhk.hkadbus2.repository.impl;

import com.google.common.collect.ImmutableMap;
import com.taxidriverhk.hkadbus2.model.entity.CategoryEntity;
import com.taxidriverhk.hkadbus2.repository.CategoryRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class CategorySqlRepository implements CategoryRepository {

    @Override
    public List<CategoryEntity> getCategories() {
        return Collections.singletonList(
                CategoryEntity.builder()
                        .name(ImmutableMap.of(
                                "en_us", "Food",
                                "zh_hk", "食品"))
                        .hashKey("food")
                        .thumbnail("http://thunbnail.jpg")
                        .build());
    }

    @Override
    public Optional<CategoryEntity> getCategoryByHashKey(String hashKey) {
        return Optional.empty();
    }
}
