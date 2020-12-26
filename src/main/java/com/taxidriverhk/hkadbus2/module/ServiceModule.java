package com.taxidriverhk.hkadbus2.module;

import com.google.common.collect.ImmutableMap;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import com.taxidriverhk.hkadbus2.service.CategoryService;
import com.taxidriverhk.hkadbus2.service.impl.CategoryServiceImpl;

import java.util.Map;

public class ServiceModule extends JerseyServletModule {

    @Override
    protected void configureServlets() {
        install(new DataAccessModule());

        bind(CategoryService.class).to(CategoryServiceImpl.class);

        final Map<String, String> serveParams = ImmutableMap.of(
                PackagesResourceConfig.PROPERTY_PACKAGES, "com.taxidriverhk.hkadbus2.activity");
        serve("/api/*").with(GuiceContainer.class, serveParams);
    }
}
