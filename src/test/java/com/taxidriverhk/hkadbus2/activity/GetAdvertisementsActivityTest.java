package com.taxidriverhk.hkadbus2.activity;

import com.taxidriverhk.hkadbus2.exception.BadRequestException;
import com.taxidriverhk.hkadbus2.model.api.GetAdvertisementsResponse;
import com.taxidriverhk.hkadbus2.model.domain.Advertisement;
import com.taxidriverhk.hkadbus2.service.AdvertisementService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.ws.rs.core.Response;
import java.util.Collections;

import static com.taxidriverhk.hkadbus2.util.MockDataHelper.CATEGORY_HASH_KEY_1;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.LANGUAGE_EN;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetAdvertisementsActivityTest {

    @Mock
    private AdvertisementService advertisementService;

    @Mock
    private Advertisement advertisement;

    @InjectMocks
    private GetAdvertisementsActivity activity;

    @Test
    public void GIVEN_categoryId_WHEN_getAdvertisements_THEN_shouldReturnValidResponse() {
        when(advertisementService.getAdvertisements(any(), any())).thenReturn(Collections.singletonList(advertisement));

        final Response response = activity.list(CATEGORY_HASH_KEY_1, LANGUAGE_EN);
        final GetAdvertisementsResponse getAdvertisementsResponse = (GetAdvertisementsResponse) response.getEntity();
        assertThat(getAdvertisementsResponse.getAdvertisements(), containsInAnyOrder(advertisement));
    }

    @Test
    public void GIVEN_invalidLanguage_WHEN_getAdvertisements_THEN_shouldThrowException() {
        assertThrows(BadRequestException.class, () -> activity.list(CATEGORY_HASH_KEY_1, "invalid-language"));
    }
}
