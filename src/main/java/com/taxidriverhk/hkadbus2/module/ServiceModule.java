package com.taxidriverhk.hkadbus2.module;

import com.taxidriverhk.hkadbus2.repository.AdvertisementRepository;
import com.taxidriverhk.hkadbus2.repository.BusBrandRepository;
import com.taxidriverhk.hkadbus2.repository.BusModelRepository;
import com.taxidriverhk.hkadbus2.repository.BusRepository;
import com.taxidriverhk.hkadbus2.repository.BusRouteRepository;
import com.taxidriverhk.hkadbus2.repository.CategoryRepository;
import com.taxidriverhk.hkadbus2.repository.PhotoRepository;
import com.taxidriverhk.hkadbus2.service.AdvertisementService;
import com.taxidriverhk.hkadbus2.service.BusService;
import com.taxidriverhk.hkadbus2.service.CategoryService;
import com.taxidriverhk.hkadbus2.service.PhotoService;
import com.taxidriverhk.hkadbus2.service.impl.AdvertisementServiceImpl;
import com.taxidriverhk.hkadbus2.service.impl.BusServiceImpl;
import com.taxidriverhk.hkadbus2.service.impl.CategoryServiceImpl;
import com.taxidriverhk.hkadbus2.service.impl.PhotoServiceImpl;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class ServiceModule {

    @Provides
    @Singleton
    public AdvertisementService advertisementService(
            final AdvertisementRepository advertisementRepository,
            final CategoryRepository categoryRepository
    ) {
        return new AdvertisementServiceImpl(advertisementRepository, categoryRepository);
    }

    @Provides
    @Singleton
    public BusService busService(
            final BusBrandRepository busBrandRepository,
            final BusModelRepository busModelRepository
    ) {
        return new BusServiceImpl(busBrandRepository, busModelRepository);
    }

    @Provides
    @Singleton
    public CategoryService categoryService(final CategoryRepository categoryRepository) {
        return new CategoryServiceImpl(categoryRepository);
    }

    @Provides
    @Singleton
    public PhotoService photoService(
            final AdvertisementRepository advertisementRepository,
            final BusRepository busRepository,
            final BusModelRepository busModelRepository,
            final BusRouteRepository busRouteRepository,
            final PhotoRepository photoRepository
    ) {
        return new PhotoServiceImpl(
                advertisementRepository,
                busRepository,
                busModelRepository,
                busRouteRepository,
                photoRepository);
    }
}
