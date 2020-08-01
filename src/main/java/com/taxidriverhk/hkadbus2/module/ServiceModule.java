package com.taxidriverhk.hkadbus2.module;

import com.taxidriverhk.hkadbus2.repository.CategoryRepository;
import com.taxidriverhk.hkadbus2.service.CategoryService;
import com.taxidriverhk.hkadbus2.service.impl.CategoryServiceImpl;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class ServiceModule {

    @Provides
    @Singleton
    public CategoryService categoryService(final CategoryRepository categoryRepository) {
        return new CategoryServiceImpl(categoryRepository);
    }
}
