package com.taxidriverhk.hkadbus2.util;

import com.taxidriverhk.hkadbus2.model.domain.Category;
import com.taxidriverhk.hkadbus2.model.entity.CategoryEntity;

public final class MockDataHelper {

    public static final Category CATEGORY = Category.builder()
            .id("1")
            .name("Test Category")
            .thumbnail("http://thunbnail.jpg")
            .build();

    public static final CategoryEntity CATEGORY_ENTITY = CategoryEntity.builder()
            .id(1)
            .nameTranslationId(1)
            .sku("test-category")
            .thumbnail("http://thunbnail.jpg")
            .build();
}
