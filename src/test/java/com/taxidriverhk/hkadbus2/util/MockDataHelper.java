package com.taxidriverhk.hkadbus2.util;

import com.taxidriverhk.hkadbus2.model.domain.Category;

public final class MockDataHelper {

    public static final Category CATEGORY = Category.builder()
            .id("1")
            .name("Test Category")
            .thumbnail("http://thunbnail.jpg")
            .build();
}
