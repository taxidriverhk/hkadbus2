package com.taxidriverhk.hkadbus2.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.taxidriverhk.hkadbus2.repository.CategoryRepository;
import com.taxidriverhk.hkadbus2.repository.impl.CategorySqlRepository;
import lombok.extern.log4j.Log4j2;

import javax.inject.Named;

@Log4j2
public class DataAccessModule extends AbstractModule {

    @Provides
    @Singleton
    public CategoryRepository categoryRepository(
            @Named("datasource.postgresql.connectionString") final String connectionString
    ) {
        log.info("Connection string: {}", connectionString);
        return new CategorySqlRepository();
    }

    @Override
    protected void configure() {
        install(new EnvironmentModule());
    }
}
