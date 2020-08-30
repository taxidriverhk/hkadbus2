package com.taxidriverhk.hkadbus2.service.impl;

import com.taxidriverhk.hkadbus2.mapper.EntityMapper;
import com.taxidriverhk.hkadbus2.model.domain.Category;
import com.taxidriverhk.hkadbus2.model.entity.CategoryEntity;
import com.taxidriverhk.hkadbus2.repository.CategoryRepository;
import com.taxidriverhk.hkadbus2.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@RequiredArgsConstructor(onConstructor = @__({@Inject}))
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> getCategories(final String language) {
        log.info("Fetching all categories from database.");
        final List<CategoryEntity> categoryEntities = categoryRepository.getCategories();
        return categoryEntities.stream()
                .map(categoryEntity -> EntityMapper.INSTANCE.categoryEntityToCategory(categoryEntity, language))
                .collect(Collectors.toList());
    }
}
