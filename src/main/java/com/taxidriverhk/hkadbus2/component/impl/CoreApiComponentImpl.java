package com.taxidriverhk.hkadbus2.component.impl;

import com.taxidriverhk.hkadbus2.component.CoreApiComponent;
import com.taxidriverhk.hkadbus2.module.ServiceModule;
import com.taxidriverhk.hkadbus2.service.CategoryService;

public class CoreApiComponentImpl implements CoreApiComponent {

    private ServiceModule serviceModule;

    private CoreApiComponentImpl() {
        serviceModule = new ServiceModule();
    }

    @Override
    public CategoryService categoryService() {
        return serviceModule.categoryService();
    }

    public static CoreApiComponent create() {
        return new CoreApiComponentImpl();
    }
}
