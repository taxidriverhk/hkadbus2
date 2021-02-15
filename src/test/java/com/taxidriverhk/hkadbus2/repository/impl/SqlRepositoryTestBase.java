package com.taxidriverhk.hkadbus2.repository.impl;

import com.taxidriverhk.hkadbus2.model.entity.AdvertisementEntity;
import com.taxidriverhk.hkadbus2.model.entity.BusBrandEntity;
import com.taxidriverhk.hkadbus2.model.entity.BusEntity;
import com.taxidriverhk.hkadbus2.model.entity.BusModelEntity;
import com.taxidriverhk.hkadbus2.model.entity.BusRouteEntity;
import com.taxidriverhk.hkadbus2.model.entity.CategoryEntity;
import com.taxidriverhk.hkadbus2.model.entity.PhotoEntity;
import com.taxidriverhk.hkadbus2.model.entity.SearchRecordEntity;
import com.taxidriverhk.hkadbus2.model.entity.UserEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.BeforeEach;

import java.util.Properties;

public abstract class SqlRepositoryTestBase {

    protected SessionFactory sessionFactory;

    @BeforeEach
    public void setupBase() {
        final Properties properties = new Properties();
        properties.setProperty("hibernate.connection.url", "jdbc:h2:mem:testdb");
        properties.setProperty("dialect", "org.hibernate.dialect.H2Dialect");
        properties.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
        properties.setProperty("hibernate.hbm2ddl.auto", "create");
        properties.setProperty("hibernate.hbm2ddl.show_sql", "true");
        properties.setProperty("hibernate.globally_quoted_identifiers", "true");

        sessionFactory = new Configuration()
                .addAnnotatedClass(AdvertisementEntity.class)
                .addAnnotatedClass(BusEntity.class)
                .addAnnotatedClass(BusBrandEntity.class)
                .addAnnotatedClass(BusModelEntity.class)
                .addAnnotatedClass(BusRouteEntity.class)
                .addAnnotatedClass(CategoryEntity.class)
                .addAnnotatedClass(SearchRecordEntity.class)
                .addAnnotatedClass(PhotoEntity.class)
                .addAnnotatedClass(UserEntity.class)
                .setProperties(properties)
                .buildSessionFactory();

        final Session session = sessionFactory.openSession();
        setupDataForTest(session);
        session.close();
    }

    protected abstract void setupDataForTest(final Session session);
}
