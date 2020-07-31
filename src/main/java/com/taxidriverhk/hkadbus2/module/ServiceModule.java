package com.taxidriverhk.hkadbus2.module;

import com.taxidriverhk.hkadbus2.service.CategoryService;
import com.taxidriverhk.hkadbus2.service.impl.CategoryServiceImpl;

public class ServiceModule {

    public CategoryService categoryService() {
        return new CategoryServiceImpl();
    }
}
