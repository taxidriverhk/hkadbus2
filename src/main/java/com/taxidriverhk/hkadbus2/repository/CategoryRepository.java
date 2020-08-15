package com.taxidriverhk.hkadbus2.repository;

import com.taxidriverhk.hkadbus2.model.entity.CategoryEntity;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {

    List<CategoryEntity> getCategories();
    Optional<CategoryEntity> getCategoryByHashKey(String hashKey);
}
