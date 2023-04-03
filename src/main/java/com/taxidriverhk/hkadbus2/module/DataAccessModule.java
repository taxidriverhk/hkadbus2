package com.taxidriverhk.hkadbus2.module;

import java.util.Properties;

import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.taxidriverhk.hkadbus2.model.entity.AdvertisementEntity;
import com.taxidriverhk.hkadbus2.model.entity.BusBrandEntity;
import com.taxidriverhk.hkadbus2.model.entity.BusEntity;
import com.taxidriverhk.hkadbus2.model.entity.BusModelEntity;
import com.taxidriverhk.hkadbus2.model.entity.BusRouteEntity;
import com.taxidriverhk.hkadbus2.model.entity.CategoryEntity;
import com.taxidriverhk.hkadbus2.model.entity.PhotoEntity;
import com.taxidriverhk.hkadbus2.model.entity.SearchRecordEntity;
import com.taxidriverhk.hkadbus2.model.entity.UserEntity;
import com.taxidriverhk.hkadbus2.provider.SearchPhotoProvider;
import com.taxidriverhk.hkadbus2.provider.impl.SearchPhotoProviderImpl;
import com.taxidriverhk.hkadbus2.repository.AdvertisementRepository;
import com.taxidriverhk.hkadbus2.repository.BusBrandRepository;
import com.taxidriverhk.hkadbus2.repository.BusModelRepository;
import com.taxidriverhk.hkadbus2.repository.BusRepository;
import com.taxidriverhk.hkadbus2.repository.BusRouteRepository;
import com.taxidriverhk.hkadbus2.repository.CategoryRepository;
import com.taxidriverhk.hkadbus2.repository.PhotoRepository;
import com.taxidriverhk.hkadbus2.repository.UserRepository;
import com.taxidriverhk.hkadbus2.repository.impl.AdvertisementSqlRepository;
import com.taxidriverhk.hkadbus2.repository.impl.BusBrandSqlRepository;
import com.taxidriverhk.hkadbus2.repository.impl.BusModelSqlRepository;
import com.taxidriverhk.hkadbus2.repository.impl.BusRoutelSqlRepository;
import com.taxidriverhk.hkadbus2.repository.impl.BusSqlRepository;
import com.taxidriverhk.hkadbus2.repository.impl.CategorySqlRepository;
import com.taxidriverhk.hkadbus2.repository.impl.PhotoSqlRepository;
import com.taxidriverhk.hkadbus2.repository.impl.UserSqlRepository;

import lombok.extern.log4j.Log4j2;

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
        final String urlOverride = System.getenv("HKADBUS2_DATABASE_URL");
        final String usernameOverride = System.getenv("HKADBUS2_DATABASE_USERNAME");
        final String passwordOverride = System.getenv("HKADBUS2_DATABASE_PASSWORD");
        final String driverClassOverride = System.getenv("HKADBUS2_DATABASE_DRIVER_CLASS");
        final String dialectOverride = System.getenv("HKADBUS2_DATABASE_DIALECT");

        final String urlToUse = StringUtils.isNotEmpty(urlOverride) ? urlOverride : url;
        final String usernameToUse = StringUtils.isNotEmpty(usernameOverride) ? usernameOverride : username;
        final String passwordToUse = StringUtils.isNotEmpty(passwordOverride) ? passwordOverride : password;
        final String driverClassToUse = StringUtils.isNotEmpty(driverClassOverride) ? driverClassOverride : driverClass;
        final String dialectToUse = StringUtils.isNotEmpty(dialectOverride) ? dialectOverride : dialect;

        log.info("Connecting to SQL database with URL {}", urlToUse);

        final Properties properties = new Properties();
        properties.setProperty("hibernate.connection.url", urlToUse);
        properties.setProperty("hibernate.connection.username", usernameToUse);
        properties.setProperty("hibernate.connection.password", passwordToUse);
        properties.setProperty("hibernate.connection.driver_class", driverClassToUse);
        properties.setProperty("hibernate.globally_quoted_identifiers", "true");
        properties.setProperty("dialect", dialectToUse);

        return new Configuration()
                .addAnnotatedClass(AdvertisementEntity.class)
                .addAnnotatedClass(BusEntity.class)
                .addAnnotatedClass(BusBrandEntity.class)
                .addAnnotatedClass(BusModelEntity.class)
                .addAnnotatedClass(BusRouteEntity.class)
                .addAnnotatedClass(CategoryEntity.class)
                .addAnnotatedClass(PhotoEntity.class)
                .addAnnotatedClass(SearchRecordEntity.class)
                .addAnnotatedClass(UserEntity.class)
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
    public BusRepository busRepository(final SessionFactory sessionFactory) {
        return new BusSqlRepository(sessionFactory);
    }

    @Provides
    @Singleton
    public BusModelRepository busModelRepository(final SessionFactory sessionFactory) {
        return new BusModelSqlRepository(sessionFactory);
    }

    @Provides
    @Singleton
    public BusBrandRepository busBrandRepository(final SessionFactory sessionFactory) {
        return new BusBrandSqlRepository(sessionFactory);
    }

    @Provides
    @Singleton
    public BusRouteRepository busRouteRepository(final SessionFactory sessionFactory) {
        return new BusRoutelSqlRepository(sessionFactory);
    }

    @Provides
    @Singleton
    public CategoryRepository categoryRepository(final SessionFactory sessionFactory) {
        return new CategorySqlRepository(sessionFactory);
    }

    @Provides
    @Singleton
    public PhotoRepository photoRepository(final SessionFactory sessionFactory) {
        return new PhotoSqlRepository(sessionFactory);
    }

    @Provides
    @Singleton
    public UserRepository userRepository(final SessionFactory sessionFactory) {
        return new UserSqlRepository(sessionFactory);
    }

    @Provides
    @Singleton
    public SearchPhotoProvider searchPhotoProvider(final SessionFactory sessionFactory) {
        return new SearchPhotoProviderImpl(sessionFactory);
    }

    @Override
    protected void configure() {
        install(new EnvironmentModule());
    }
}
