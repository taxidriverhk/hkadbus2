package com.taxidriverhk.hkadbus2.activity;

import static com.taxidriverhk.hkadbus2.util.MockDataHelper.PHOTO_SHORT_ID_1;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import javax.ws.rs.core.Response;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.taxidriverhk.hkadbus2.model.api.PutPhotoRequest;
import com.taxidriverhk.hkadbus2.model.api.PutPhotoResponse;
import com.taxidriverhk.hkadbus2.service.PhotoService;

@ExtendWith(MockitoExtension.class)
public class PutPhotoActivityTest {

    @Mock
    private PhotoService photoService;

    @Mock
    private PutPhotoRequest request;

    @InjectMocks
    private PutPhotoActivity activity;

    @Test
    public void GIVEN_validPhotoRequest_WHEN_putPhoto_THEN_shouldReturnValidResponse() {
        when(photoService.putPhoto(any())).thenReturn(PHOTO_SHORT_ID_1);

        final Response response = activity.put(request);
        final PutPhotoResponse getPhotoResponse = (PutPhotoResponse) response.getEntity();

        assertThat(getPhotoResponse.getPhotoId(), equalTo(PHOTO_SHORT_ID_1));
    }
}
