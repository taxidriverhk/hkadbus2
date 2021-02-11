package com.taxidriverhk.hkadbus2.activity;

import com.taxidriverhk.hkadbus2.exception.BadRequestException;
import com.taxidriverhk.hkadbus2.model.api.GetPhotoResponse;
import com.taxidriverhk.hkadbus2.model.domain.Photo;
import com.taxidriverhk.hkadbus2.service.PhotoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.ws.rs.core.Response;

import static com.taxidriverhk.hkadbus2.util.MockDataHelper.LANGUAGE_EN;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.PHOTO_SHORT_ID_1;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetPhotoActivityTest {

    @Mock
    private PhotoService photoService;

    @Mock
    private Photo photo;

    @InjectMocks
    private GetPhotoActivity activity;

    @Test
    public void GIVEN_validPhotoId_WHEN_getPhoto_THEN_shouldReturnValidResponse() {
        when(photoService.getPhoto(any(), any())).thenReturn(photo);

        final Response response = activity.get(PHOTO_SHORT_ID_1, LANGUAGE_EN);
        final GetPhotoResponse getPhotoResponse = (GetPhotoResponse) response.getEntity();

        assertThat(getPhotoResponse.getPhoto(), equalTo(photo));
    }

    @Test
    public void GIVEN_invalidLanguage_WHEN_getPhoto_THEN_shouldThrowException() {
        assertThrows(BadRequestException.class, () -> activity.get(PHOTO_SHORT_ID_1, "invalid-language"));
    }
}
