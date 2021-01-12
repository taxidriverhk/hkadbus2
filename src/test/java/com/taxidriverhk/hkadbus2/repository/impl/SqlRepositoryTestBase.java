package com.taxidriverhk.hkadbus2.repository.impl;

import com.taxidriverhk.hkadbus2.model.entity.CategoryEntity;
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

        sessionFactory = new Configuration()
                .addAnnotatedClass(CategoryEntity.class)
                .setProperties(properties)
                .buildSessionFactory();

        final Session session = sessionFactory.openSession();
        setupDataForTest(session);
        session.close();
    }

    protected abstract void setupDataForTest(final Session session);
}
