package com.taxidriverhk.hkadbus2.component;

import com.taxidriverhk.hkadbus2.module.DataAccessModule;
import com.taxidriverhk.hkadbus2.module.EnvironmentModule;
import com.taxidriverhk.hkadbus2.module.ServiceModule;
import com.taxidriverhk.hkadbus2.service.CategoryService;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {
        EnvironmentModule.class,
        DataAccessModule.class,
        ServiceModule.class
})
public interface CoreApiComponent {

    CategoryService categoryService();
}
