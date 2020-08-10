package com.taxidriverhk.hkadbus2.util;

import com.google.common.collect.ImmutableMap;
import com.taxidriverhk.hkadbus2.model.domain.Category;
import com.taxidriverhk.hkadbus2.model.entity.CategoryEntity;

public final class MockDataHelper {

    public static final String LANGUAGE_EN = "en_us";
    public static final String LANGUAGE_ZH = "zh_hk";

    public static final Category CATEGORY = Category.builder()
            .id("1")
            .name("Test Category")
            .thumbnail("http://thunbnail.jpg")
            .build();

    public static final CategoryEntity CATEGORY_ENTITY = CategoryEntity.builder()
            .name(ImmutableMap.of(
                    LANGUAGE_EN, "Food",
                    LANGUAGE_ZH, "食品"))
            .hashKey("test-category")
            .thumbnail("http://thunbnail.jpg")
            .build();
}
