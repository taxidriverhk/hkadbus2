package com.taxidriverhk.hkadbus2.component;

import com.taxidriverhk.hkadbus2.module.DataAccessModule;
import com.taxidriverhk.hkadbus2.module.EnvironmentModule;
import com.taxidriverhk.hkadbus2.module.ServiceModule;
import com.taxidriverhk.hkadbus2.service.AdvertisementService;
import com.taxidriverhk.hkadbus2.service.BusService;
import com.taxidriverhk.hkadbus2.service.CategoryService;
import com.taxidriverhk.hkadbus2.service.PhotoService;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {
        EnvironmentModule.class,
        DataAccessModule.class,
        ServiceModule.class
})
public interface CoreApiComponent {

    AdvertisementService advertisementService();
    BusService busService();
    CategoryService categoryService();
    PhotoService photoService();
}
