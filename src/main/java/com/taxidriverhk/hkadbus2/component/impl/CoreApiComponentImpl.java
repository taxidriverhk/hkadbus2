package com.taxidriverhk.hkadbus2.component.impl;

import com.taxidriverhk.hkadbus2.component.CoreApiComponent;
import com.taxidriverhk.hkadbus2.module.DataAccessModule;
import com.taxidriverhk.hkadbus2.module.EnvironmentModule;
import com.taxidriverhk.hkadbus2.module.ServiceModule;
import com.taxidriverhk.hkadbus2.repository.CategoryRepository;
import com.taxidriverhk.hkadbus2.service.CategoryService;
import org.hibernate.SessionFactory;

import java.util.Properties;

public class CoreApiComponentImpl implements CoreApiComponent {

    private EnvironmentModule environmentModule;
    private DataAccessModule dataAccessModule;
    private ServiceModule serviceModule;

    private CoreApiComponentImpl() {
        environmentModule = new EnvironmentModule();
        dataAccessModule = new DataAccessModule();
        serviceModule = new ServiceModule();
    }

    @Override
    public CategoryService categoryService() {
        final Properties applicationProperties = environmentModule.applicationProperties();
        final SessionFactory sessionFactory = dataAccessModule.sessionFactory(applicationProperties);
        final CategoryRepository categoryRepository = dataAccessModule.categoryRepository(sessionFactory);
        return serviceModule.categoryService(categoryRepository);
    }

    public static CoreApiComponent create() {
        return new CoreApiComponentImpl();
    }
}
