package com.taxidriverhk.hkadbus2.activity;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.taxidriverhk.hkadbus2.module.ServiceModule;

public class ApplicationServletContextListener extends GuiceServletContextListener {

    @Override
    public Injector getInjector() {
        return Guice.createInjector(new ServiceModule());
    }
}
