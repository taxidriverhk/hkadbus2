package com.taxidriverhk.hkadbus2.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.taxidriverhk.hkadbus2.repository.CategoryRepository;
import com.taxidriverhk.hkadbus2.repository.impl.CategorySqlRepository;

public class DataAccessModule extends AbstractModule {

    @Provides
    @Singleton
    public CategoryRepository categoryRepository() {
        return new CategorySqlRepository();
    }

    @Override
    protected void configure() {
        install(new EnvironmentModule());
    }
}
