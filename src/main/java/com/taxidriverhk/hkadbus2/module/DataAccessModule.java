package com.taxidriverhk.hkadbus2.module;

import com.taxidriverhk.hkadbus2.model.entity.CategoryEntity;
import com.taxidriverhk.hkadbus2.repository.CategoryRepository;
import com.taxidriverhk.hkadbus2.repository.impl.CategorySqlRepository;
import org.hibernate.SessionFactory;

import java.util.Base64;
import java.util.Properties;

public class DataAccessModule {

    public SessionFactory sessionFactory(final Properties applicationProperties) {
        final Properties properties = new Properties();
        properties.setProperty("hibernate.connection.url", applicationProperties.getProperty("datasource.sql.url"));
        properties.setProperty("hibernate.connection.username", applicationProperties.getProperty("datasource.sql.username"));
        properties.setProperty("hibernate.connection.password", new String(Base64.getDecoder().decode(applicationProperties.getProperty("datasource.sql.password"))));
        properties.setProperty("dialect", applicationProperties.getProperty("datasource.sql.dialect"));
        properties.setProperty("hibernate.connection.driver_class", applicationProperties.getProperty("datasource.sql.driverClass"));

        return new org.hibernate.cfg.Configuration()
                .addAnnotatedClass(CategoryEntity.class)
                .addProperties(properties)
                .buildSessionFactory();
    }

    public CategoryRepository categoryRepository(final SessionFactory sessionFactory) {
        return new CategorySqlRepository(sessionFactory);
    }
}
