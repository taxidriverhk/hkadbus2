package com.taxidriverhk.hkadbus2.module;

import com.google.common.collect.ImmutableMap;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import com.taxidriverhk.hkadbus2.activity.filter.AuthenticationFilter;
import com.taxidriverhk.hkadbus2.activity.filter.LoggingFilter;
import com.taxidriverhk.hkadbus2.service.CategoryService;
import com.taxidriverhk.hkadbus2.service.impl.CategoryServiceImpl;

import java.util.Map;

public class ServiceModule extends JerseyServletModule {

    private static final String PACKAGE_NAME = "com.taxidriverhk.hkadbus2.activity";
    private static final String API_URI_PATTERN = "/api/*";

    @Override
    protected void configureServlets() {
        install(new DataAccessModule());

        bind(CategoryService.class).to(CategoryServiceImpl.class);

        final Map<String, String> serveParams = ImmutableMap.of(
                PackagesResourceConfig.PROPERTY_PACKAGES, PACKAGE_NAME);
        filter(API_URI_PATTERN).through(AuthenticationFilter.class);
        filter(API_URI_PATTERN).through(LoggingFilter.class);
        serve(API_URI_PATTERN).with(GuiceContainer.class, serveParams);
    }
}
