package com.taxidriverhk.hkadbus2.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.taxidriverhk.hkadbus2.model.entity.AdvertisementEntity;
import com.taxidriverhk.hkadbus2.model.entity.CategoryEntity;
import com.taxidriverhk.hkadbus2.repository.AdvertisementRepository;
import com.taxidriverhk.hkadbus2.repository.CategoryRepository;
import com.taxidriverhk.hkadbus2.repository.impl.AdvertisementSqlRepository;
import com.taxidriverhk.hkadbus2.repository.impl.CategorySqlRepository;
import lombok.extern.log4j.Log4j2;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.inject.Named;
import java.util.Properties;

@Log4j2
public class DataAccessModule extends AbstractModule {

    @Provides
    @Singleton
    public SessionFactory sessionFactory(
            @Named("datasource.sql.url") final String url,
            @Named("datasource.sql.username") final String username,
            @Named("datasource.sql.password") final String password,
            @Named("datasource.sql.driverClass") final String driverClass,
            @Named("datasource.sql.dialect") final String dialect
    ) {
        final Properties properties = new Properties();
        properties.setProperty("hibernate.connection.url", url);
        properties.setProperty("hibernate.connection.username", username);
        properties.setProperty("hibernate.connection.password", password);
        properties.setProperty("hibernate.connection.driver_class", driverClass);
        properties.setProperty("dialect", dialect);

        return new Configuration()
                .addAnnotatedClass(AdvertisementEntity.class)
                .addAnnotatedClass(CategoryEntity.class)
                .addProperties(properties)
                .buildSessionFactory();
    }

    @Provides
    @Singleton
    public AdvertisementRepository advertisementRepository(final SessionFactory sessionFactory) {
        return new AdvertisementSqlRepository(sessionFactory);
    }

    @Provides
    @Singleton
    public CategoryRepository categoryRepository(final SessionFactory sessionFactory) {
        return new CategorySqlRepository(sessionFactory);
    }

    @Override
    protected void configure() {
        install(new EnvironmentModule());
    }
}
