package com.taxidriverhk.hkadbus2.repository;

import java.util.List;
import java.util.Optional;

import com.taxidriverhk.hkadbus2.model.entity.CategoryEntity;

public interface CategoryRepository {

    List<CategoryEntity> getCategories();
    Optional<CategoryEntity> getCategoryByHashKey(String hashKey);
    String putCategory(CategoryEntity category);
}
