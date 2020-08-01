package com.taxidriverhk.hkadbus2.service.impl;

import com.taxidriverhk.hkadbus2.mapper.EntityMapper;
import com.taxidriverhk.hkadbus2.model.domain.Category;
import com.taxidriverhk.hkadbus2.model.entity.CategoryEntity;
import com.taxidriverhk.hkadbus2.repository.CategoryRepository;
import com.taxidriverhk.hkadbus2.service.CategoryService;
import lombok.RequiredArgsConstructor;

import javax.inject.Inject;
import java.util.List;

@RequiredArgsConstructor(onConstructor = @__({@Inject}))
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> getCategories() {
        final List<CategoryEntity> categoryEntities = categoryRepository.getCategories();
        return EntityMapper.INSTANCE.categoryEntitiesToCategories(categoryEntities);
    }
}
