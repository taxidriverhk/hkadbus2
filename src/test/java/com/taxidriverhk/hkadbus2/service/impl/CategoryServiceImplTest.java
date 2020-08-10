package com.taxidriverhk.hkadbus2.service.impl;

import com.google.common.collect.Lists;
import com.taxidriverhk.hkadbus2.model.domain.Category;
import com.taxidriverhk.hkadbus2.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.taxidriverhk.hkadbus2.util.MockDataHelper.CATEGORY_ENTITY;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.LANGUAGE_EN;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceImplTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Test
    public void WHEN_getAllCategories_THEN_shouldGetEntitiesFromRepository() {
        when(categoryRepository.getCategories()).thenReturn(Lists.newArrayList(CATEGORY_ENTITY));
        final List<Category> categories = categoryService.getCategories(LANGUAGE_EN);
        assertThat(categories, hasSize(1));
    }
}
