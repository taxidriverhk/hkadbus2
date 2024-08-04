package com.taxidriverhk.hkadbus2.module;

import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import com.taxidriverhk.hkadbus2.activity.filter.AuthenticationFilter;
import com.taxidriverhk.hkadbus2.activity.filter.LoggingFilter;
import com.taxidriverhk.hkadbus2.provider.SearchPhotoProvider;
import com.taxidriverhk.hkadbus2.service.AdvertisementService;
import com.taxidriverhk.hkadbus2.service.BusService;
import com.taxidriverhk.hkadbus2.service.CategoryService;
import com.taxidriverhk.hkadbus2.service.PhotoService;
import com.taxidriverhk.hkadbus2.service.UserService;
import com.taxidriverhk.hkadbus2.service.async.SearchRecordInsertionAsyncHandler;
import com.taxidriverhk.hkadbus2.service.impl.AdvertisementServiceImpl;
import com.taxidriverhk.hkadbus2.service.impl.BusServiceImpl;
import com.taxidriverhk.hkadbus2.service.impl.CategoryServiceImpl;
import com.taxidriverhk.hkadbus2.service.impl.PhotoServiceImpl;
import com.taxidriverhk.hkadbus2.service.impl.UserServiceImpl;

public class ServiceModule extends JerseyServletModule {

    private static final String PACKAGE_NAME = "com.taxidriverhk.hkadbus2.activity";
    private static final String API_URI_PATTERN = "/api/*";

    @Override
    protected void configureServlets() {
        install(new DataAccessModule());

        bind(AdvertisementService.class).to(AdvertisementServiceImpl.class);
        bind(BusService.class).to(BusServiceImpl.class);
        bind(CategoryService.class).to(CategoryServiceImpl.class);
        bind(PhotoService.class).to(PhotoServiceImpl.class);
        bind(UserService.class).to(UserServiceImpl.class);

        final Map<String, String> serveParams = ImmutableMap.of(
                PackagesResourceConfig.PROPERTY_PACKAGES, PACKAGE_NAME);
        filter(API_URI_PATTERN).through(AuthenticationFilter.class);
        filter(API_URI_PATTERN).through(LoggingFilter.class);
        serve(API_URI_PATTERN).with(GuiceContainer.class, serveParams);
    }

    @Provides
    @Singleton
    public SearchRecordInsertionAsyncHandler searchRecordInsertionAsyncHandler(final SearchPhotoProvider searchPhotoProvider) {
        return new SearchRecordInsertionAsyncHandler(searchPhotoProvider);
    }
}
