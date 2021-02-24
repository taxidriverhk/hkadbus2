package com.taxidriverhk.hkadbus2.service.impl;

import com.google.common.collect.Lists;
import com.taxidriverhk.hkadbus2.exception.BadRequestException;
import com.taxidriverhk.hkadbus2.exception.ItemNotFoundException;
import com.taxidriverhk.hkadbus2.model.domain.Photo;
import com.taxidriverhk.hkadbus2.model.domain.SearchPhotoFilter;
import com.taxidriverhk.hkadbus2.model.domain.SearchPhotoResult;
import com.taxidriverhk.hkadbus2.model.entity.result.SearchRecordResult;
import com.taxidriverhk.hkadbus2.provider.SearchPhotoProvider;
import com.taxidriverhk.hkadbus2.repository.PhotoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static com.taxidriverhk.hkadbus2.util.MockDataHelper.LANGUAGE_EN;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.PHOTO_1;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.PHOTO_ENTITY_1;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.PHOTO_SHORT_ID_1;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.SEARCH_RECORD_1;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.SEARCH_RECORD_ENTITY_1;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PhotoServiceImplTest {

    @Mock
    private PhotoRepository photoRepository;

    @Mock
    private SearchPhotoProvider searchPhotoProvider;

    @Mock
    private SearchPhotoFilter searchPhotoFilter;

    @InjectMocks
    private PhotoServiceImpl photoService;

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
                null);

        verify(searchPhotoProvider, times(1)).searchPhotos(
                Lists.newArrayList("3av55", "mcdonalds"),
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
                null);

        verify(searchPhotoProvider, times(1)).searchPhotos(
                Collections.EMPTY_LIST,
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
}
