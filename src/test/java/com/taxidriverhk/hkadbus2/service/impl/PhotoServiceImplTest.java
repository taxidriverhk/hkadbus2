package com.taxidriverhk.hkadbus2.service.impl;

import static com.taxidriverhk.hkadbus2.util.MockDataHelper.ADVERTISEMENT_ENTITY_1;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.BUS_BRAND_ENTITY_1;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.BUS_ENTITY_1;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.BUS_MODEL_ENTITY_1;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.BUS_ROUTE_ENTITY_1;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.CATEGORY_ENTITY_1;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.LANGUAGE_EN;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.PHOTO_1;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.PHOTO_ENTITY_1;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.PHOTO_SHORT_ID_1;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.PUT_PHOTO_REQUEST;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.SEARCH_RECORD_1;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.SEARCH_RECORD_ENTITY_1;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.USER_ENTITY_1;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.google.common.collect.Lists;
import com.taxidriverhk.hkadbus2.exception.BadRequestException;
import com.taxidriverhk.hkadbus2.exception.ItemNotFoundException;
import com.taxidriverhk.hkadbus2.model.domain.Photo;
import com.taxidriverhk.hkadbus2.model.domain.SearchPhotoFilter;
import com.taxidriverhk.hkadbus2.model.domain.SearchPhotoResult;
import com.taxidriverhk.hkadbus2.model.entity.PhotoEntity;
import com.taxidriverhk.hkadbus2.model.entity.result.SearchRecordResult;
import com.taxidriverhk.hkadbus2.provider.SearchPhotoProvider;
import com.taxidriverhk.hkadbus2.repository.AdvertisementRepository;
import com.taxidriverhk.hkadbus2.repository.BusBrandRepository;
import com.taxidriverhk.hkadbus2.repository.BusModelRepository;
import com.taxidriverhk.hkadbus2.repository.BusRepository;
import com.taxidriverhk.hkadbus2.repository.BusRouteRepository;
import com.taxidriverhk.hkadbus2.repository.CategoryRepository;
import com.taxidriverhk.hkadbus2.repository.PhotoRepository;
import com.taxidriverhk.hkadbus2.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class PhotoServiceImplTest {

    @Mock
    private AdvertisementRepository advertisementRepository;

    @Mock
    private BusRepository busRepository;

    @Mock
    private BusBrandRepository busBrandRepository;

    @Mock
    private BusModelRepository busModelRepository;

    @Mock
    private BusRouteRepository busRouteRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private PhotoRepository photoRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private SearchPhotoProvider searchPhotoProvider;

    @Mock
    private SearchPhotoFilter searchPhotoFilter;

    @InjectMocks
    private PhotoServiceImpl photoService;

    @Captor
    private ArgumentCaptor<PhotoEntity> photoEntityArgumentCaptor;

    @Test
    public void WHEN_getPhotoById_THEN_shouldGetDataFromCorrespondingRepositories() {
        when(photoRepository.getPhotoByShortId(any())).thenReturn(Optional.of(PHOTO_ENTITY_1));
        final Photo photo = photoService.getPhoto(PHOTO_SHORT_ID_1, LANGUAGE_EN);
        assertThat(photo, equalTo(PHOTO_1));
    }

    @Test
    public void WHEN_getPhotoByInvalidId_THEN_shouldThrowItemNotFoundException() {
        when(photoRepository.getPhotoByShortId(any())).thenReturn(Optional.empty());
        assertThrows(ItemNotFoundException.class, () -> photoService.getPhoto(PHOTO_SHORT_ID_1, LANGUAGE_EN));
    }

    @Test
    public void GIVEN_searchFilter_WHEN_searchPhotos_THEN_shouldIncludeNextPageCursorInResult() {
        when(searchPhotoProvider.searchPhotos(any(), any(), any(), any(), any(), anyInt())).thenReturn(SearchRecordResult.builder()
                .searchRecordEntities(Lists.newArrayList(SEARCH_RECORD_ENTITY_1))
                .total(2L)
                .nextPageCursor("test-next-page-cursor")
                .build());

        final SearchPhotoResult searchPhotoResult = photoService.searchPhotos(
                "3av55,mcdonalds",
                "uploadedDate",
                "asc",
                searchPhotoFilter,
                10,
                null);

        verify(searchPhotoProvider, times(1)).searchPhotos(
                Lists.newArrayList("3av55", "mcdonalds"),
                "uploadedDate",
                "asc",
                searchPhotoFilter,
                null,
                10);
        assertThat(searchPhotoResult, equalTo(SearchPhotoResult.builder()
                .results(Lists.newArrayList(SEARCH_RECORD_1))
                .total(2L)
                .nextPageCursor("test-next-page-cursor")
                .build()));
    }

    @Test
    public void GIVEN_searchFilterWithMultipleQueryTexts_WHEN_searchPhotos_THEN_shouldIncludeNextPageCursorInResult() {
        when(searchPhotoProvider.searchPhotos(any(), any(), any(), any(), any(), anyInt())).thenReturn(SearchRecordResult.builder()
                .searchRecordEntities(Lists.newArrayList(SEARCH_RECORD_ENTITY_1))
                .total(2L)
                .nextPageCursor("test-next-page-cursor")
                .build());

        final SearchPhotoResult searchPhotoResult = photoService.searchPhotos(
                "3av55 mcdonalds 麥當勞",
                "uploadedDate",
                "asc",
                searchPhotoFilter,
                null,
                null);

        verify(searchPhotoProvider, times(1)).searchPhotos(
                Lists.newArrayList("3av55", "mcdonalds", "麥當勞"),
                "uploadedDate",
                "asc",
                searchPhotoFilter,
                null,
                100);
        assertThat(searchPhotoResult, equalTo(SearchPhotoResult.builder()
                .results(Lists.newArrayList(SEARCH_RECORD_1))
                .total(2L)
                .nextPageCursor("test-next-page-cursor")
                .build()));
    }

    @Test
    public void GIVEN_searchFilterWithoutQuery_WHEN_searchPhotos_THEN_shouldStillReturnResults() {
        when(searchPhotoProvider.searchPhotos(any(), any(), any(), any(), any(), anyInt())).thenReturn(SearchRecordResult.builder()
                .searchRecordEntities(Lists.newArrayList(SEARCH_RECORD_ENTITY_1))
                .total(2L)
                .nextPageCursor("test-next-page-cursor")
                .build());

        final SearchPhotoResult searchPhotoResult = photoService.searchPhotos(
                null,
                "uploadedDate",
                "asc",
                searchPhotoFilter,
                null,
                null);

        verify(searchPhotoProvider, times(1)).searchPhotos(
                Collections.emptyList(),
                "uploadedDate",
                "asc",
                searchPhotoFilter,
                null,
                100);
        assertThat(searchPhotoResult, equalTo(SearchPhotoResult.builder()
                .results(Lists.newArrayList(SEARCH_RECORD_1))
                .total(2L)
                .nextPageCursor("test-next-page-cursor")
                .build()));
    }

    @Test
    public void GIVEN_photoRequestWithExistingEntities_WHEN_putPhoto_THEN_shouldInsertPhotoWithNewId() {
        when(categoryRepository.getCategoryByHashKey(any())).thenReturn(Optional.of(CATEGORY_ENTITY_1));
        when(advertisementRepository.getAdvertisementByHashKey(any())).thenReturn(Optional.of(ADVERTISEMENT_ENTITY_1));
        when(busBrandRepository.getBusBrandByHashKey(any())).thenReturn(Optional.of(BUS_BRAND_ENTITY_1));
        when(busModelRepository.getBusModelByHashKey(any())).thenReturn(Optional.of(BUS_MODEL_ENTITY_1));
        when(busRepository.getBuses(any())).thenReturn(Lists.newArrayList(BUS_ENTITY_1));
        when(busRouteRepository.getBusRouteByHashKey(any())).thenReturn(Optional.of(BUS_ROUTE_ENTITY_1));
        when(userRepository.getUserByUsername(any())).thenReturn(Optional.of(USER_ENTITY_1));

        when(photoRepository.getPhotoByShortId(anyLong())).thenReturn(Optional.empty());
        when(photoRepository.putPhoto(any())).thenReturn(PHOTO_SHORT_ID_1);

        final long photoShortId = photoService.putPhoto(PUT_PHOTO_REQUEST);

        verify(photoRepository, times(1)).putPhoto(photoEntityArgumentCaptor.capture());

        final PhotoEntity photoEntityInserted = photoEntityArgumentCaptor.getValue();
        assertThat(photoShortId, equalTo(PHOTO_SHORT_ID_1));
        assertThat(photoEntityInserted.getAdvertisement().getHashKey(), equalTo(ADVERTISEMENT_ENTITY_1.getHashKey()));
    }

    @Test
    public void GIVEN_photoRequestWithNonExistingEntities_WHEN_putPhoto_THEN_shouldInsertEntitiesAndPhotoWithNewId() {
        when(userRepository.getUserByUsername(any())).thenReturn(Optional.of(USER_ENTITY_1));

        when(categoryRepository.getCategoryByHashKey(any())).thenReturn(Optional.empty());
        when(categoryRepository.putCategory(any())).thenReturn(CATEGORY_ENTITY_1.getId().toString());

        when(advertisementRepository.getAdvertisementByHashKey(any())).thenReturn(Optional.empty());
        when(advertisementRepository.putAdvertisement(any())).thenReturn(ADVERTISEMENT_ENTITY_1.getId().toString());

        when(busBrandRepository.getBusBrandByHashKey(any())).thenReturn(Optional.empty());
        when(busBrandRepository.putBusBrand(any())).thenReturn(BUS_BRAND_ENTITY_1.getId().toString());

        when(busModelRepository.getBusModelByHashKey(any())).thenReturn(Optional.empty());
        when(busModelRepository.putBusModel(any())).thenReturn(BUS_MODEL_ENTITY_1.getId().toString());

        when(busRepository.getBuses(any())).thenReturn(Lists.newArrayList());
        when(busRepository.putBus(any())).thenReturn(BUS_ENTITY_1.getId().toString());

        when(busRouteRepository.getBusRouteByHashKey(any())).thenReturn(Optional.empty());
        when(busRouteRepository.putBusRoute(any())).thenReturn(BUS_ROUTE_ENTITY_1.getId().toString());

        when(photoRepository.getPhotoByShortId(anyLong())).thenReturn(Optional.empty());
        when(photoRepository.putPhoto(any())).thenReturn(PHOTO_SHORT_ID_1);

        final long photoShortId = photoService.putPhoto(PUT_PHOTO_REQUEST);

        verify(photoRepository, times(1)).putPhoto(photoEntityArgumentCaptor.capture());

        final PhotoEntity photoEntityInserted = photoEntityArgumentCaptor.getValue();
        assertThat(photoShortId, equalTo(PHOTO_SHORT_ID_1));
        assertThat(photoEntityInserted.getAdvertisement().getHashKey(), equalTo(ADVERTISEMENT_ENTITY_1.getHashKey()));
    }

    @Test
    public void GIVEN_photoRequest_WHEN_putPhotoHasError_THEN_shouldRollbackPreviousInsertions() {
        // TODO: implement me
        assertThat(true, equalTo(true));
    }

    @Test
    public void GIVEN_photoRequest_WHEN_invalidUsernameIsProvided_THEN_shouldThrowException() {
        when(userRepository.getUserByUsername(any())).thenReturn(Optional.empty());
        assertThrows(BadRequestException.class, () -> photoService.putPhoto(PUT_PHOTO_REQUEST));
    }
}
