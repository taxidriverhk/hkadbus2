package com.taxidriverhk.hkadbus2.service.impl;

import com.google.common.collect.Lists;
import com.taxidriverhk.hkadbus2.model.domain.Category;
import com.taxidriverhk.hkadbus2.service.CategoryService;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    @Override
    public List<Category> getCategories() {
        return Lists.newArrayList(
                Category.builder()
                        .id("1")
                        .name("Test Category")
                        .thumbnail("http://thunbnail.jpg")
                        .build());
    }
}
