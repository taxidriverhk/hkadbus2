package com.taxidriverhk.hkadbus2.service.impl;

import com.taxidriverhk.hkadbus2.exception.ItemNotFoundException;
import com.taxidriverhk.hkadbus2.repository.AdvertisementRepository;
import com.taxidriverhk.hkadbus2.repository.BusModelRepository;
import com.taxidriverhk.hkadbus2.repository.BusRepository;
import com.taxidriverhk.hkadbus2.repository.BusRouteRepository;
import com.taxidriverhk.hkadbus2.repository.PhotoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.taxidriverhk.hkadbus2.util.MockDataHelper.ADVERTISEMENT_ENTITY_1;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.ADVERTISEMENT_HASH_KEY_1;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.BUS_ENTITY_1;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.BUS_ID_1;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.BUS_MODEL_ENTITY_1;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.BUS_MODEL_HASH_KEY_1;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.BUS_ROUTE_ENTITY_1;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.BUS_ROUTE_HASH_KEY_1;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.CATEGORY_HASH_KEY_1;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.LANGUAGE_EN;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.PHOTO_1;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.PHOTO_ENTITY_1;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.PHOTO_ID_1;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PhotoServiceImplTest {

    @Mock
    private AdvertisementRepository advertisementRepository;

    @Mock
    private BusRepository busRepository;

    @Mock
    private BusModelRepository busModelRepository;

    @Mock
    private BusRouteRepository busRouteRepository;

    @Mock
    private PhotoRepository photoRepository;

    @InjectMocks
    private PhotoServiceImpl photoService;

    @Test
    public void WHEN_getPhotoById_THEN_shouldGetDataFromCorrespondingRepositories() {
        when(photoRepository.getPhoto(PHOTO_ID_1)).thenReturn(Optional.of(PHOTO_ENTITY_1));
        when(advertisementRepository.getAdvertisement(ADVERTISEMENT_HASH_KEY_1)).thenReturn(Optional.of(ADVERTISEMENT_ENTITY_1));
        when(busRepository.getBus(BUS_ID_1)).thenReturn(Optional.of(BUS_ENTITY_1));
        when(busModelRepository.getBusModelByHashKey(BUS_MODEL_HASH_KEY_1)).thenReturn(Optional.of(BUS_MODEL_ENTITY_1));
        when(busRouteRepository.getBusRouteByHashKey(BUS_ROUTE_HASH_KEY_1)).thenReturn(Optional.of(BUS_ROUTE_ENTITY_1));

        assertThat(photoService.getPhoto(PHOTO_ID_1, LANGUAGE_EN), equalTo(PHOTO_1));
    }

    @Test
    public void WHEN_getPhotoByInvalidId_THEN_shouldThrowItemNotFoundException() {
        when(photoRepository.getPhoto(any())).thenReturn(Optional.empty());
        assertThrows(ItemNotFoundException.class, () -> photoService.getPhoto(PHOTO_ID_1, LANGUAGE_EN));
    }
}
