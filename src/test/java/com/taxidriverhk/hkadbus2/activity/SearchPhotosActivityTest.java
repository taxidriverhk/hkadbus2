package com.taxidriverhk.hkadbus2.activity;

import static com.taxidriverhk.hkadbus2.util.MockDataHelper.SEARCH_RECORD_1;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;

import javax.ws.rs.core.Response;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.taxidriverhk.hkadbus2.exception.BadRequestException;
import com.taxidriverhk.hkadbus2.model.api.SearchPhotosResponse;
import com.taxidriverhk.hkadbus2.model.domain.SearchPhotoFilter;
import com.taxidriverhk.hkadbus2.model.domain.SearchPhotoResult;
import com.taxidriverhk.hkadbus2.service.PhotoService;

@ExtendWith(MockitoExtension.class)
public class SearchPhotosActivityTest {

    @Mock
    private PhotoService photoService;

    @InjectMocks
    private SearchPhotosActivity activity;

    @Test
    public void GIVEN_validSearchParameters_WHEN_searchPhotos_THEN_shouldReturnMatchingRecords() {
        when(photoService.searchPhotos(any(), any(), any(), any(), any(), any())).thenReturn(SearchPhotoResult.builder()
                .total(2L)
                .results(Collections.singletonList(SEARCH_RECORD_1))
                .nextPageCursor("last-sort-key")
                .build());

        final Response response = activity.search(
                "query-text",
                "uploadedDate",
                "asc",
                10,
                null,
                "en_us",
                null,
                null,
                Collections.singletonList("mcdonalds"),
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null);
        final SearchPhotosResponse searchPhotosResponse = (SearchPhotosResponse) response.getEntity();

        verify(photoService, times(1)).searchPhotos(
                "query-text",
                "uploadedDate",
                "asc",
                SearchPhotoFilter.builder()
                        .advertisementNames(Collections.singletonList("mcdonalds"))
                        .language("en_us")
                        .build(),
                10,
                null);
        assertThat(searchPhotosResponse, equalTo(SearchPhotosResponse.builder()
                .total(2L)
                .nextPageCursor("last-sort-key")
                .results(Collections.singletonList(SEARCH_RECORD_1))
                .build()));
    }

    @Test
    public void GIVEN_invalidSearchParameters_WHEN_searchPhotos_THEN_shouldThrowBadRequestException() {
        assertThrows(BadRequestException.class, () -> activity.search(
                "query-text",
                "invalid-order-by",
                "asc",
                null,
                null,
                "en_us",
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null));
        assertThrows(BadRequestException.class, () -> activity.search(
                "query-text",
                "uploadedDate",
                "invalid-sort-direction",
                null,
                null,
                "en_us",
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null));
        assertThrows(BadRequestException.class, () -> activity.search(
                "query-text",
                "uploadedDate",
                "asc",
                null,
                null,
                "invalid-language",
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null));
        assertThrows(BadRequestException.class, () -> activity.search(
                "query-text",
                "uploadedDate",
                "asc",
                -1,
                null,
                "en_us",
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null));
    }
}
