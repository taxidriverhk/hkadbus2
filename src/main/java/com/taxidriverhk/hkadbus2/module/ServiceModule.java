package com.taxidriverhk.hkadbus2.module;

import com.taxidriverhk.hkadbus2.repository.AdvertisementRepository;
import com.taxidriverhk.hkadbus2.repository.CategoryRepository;
import com.taxidriverhk.hkadbus2.repository.PhotoRepository;
import com.taxidriverhk.hkadbus2.service.AdvertisementService;
import com.taxidriverhk.hkadbus2.service.CategoryService;
import com.taxidriverhk.hkadbus2.service.PhotoService;
import com.taxidriverhk.hkadbus2.service.impl.AdvertisementServiceImpl;
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
    public CategoryService categoryService(final CategoryRepository categoryRepository) {
        return new CategoryServiceImpl(categoryRepository);
    }

    @Provides
    @Singleton
    public PhotoService photoService(final PhotoRepository photoRepository) {
        return new PhotoServiceImpl(photoRepository);
    }
}
