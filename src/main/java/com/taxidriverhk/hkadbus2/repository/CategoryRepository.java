package com.taxidriverhk.hkadbus2.repository;

import com.taxidriverhk.hkadbus2.model.entity.CategoryEntity;

import java.util.List;

public interface CategoryRepository {

    List<CategoryEntity> getCategories();
}
