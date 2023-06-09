package com.taxidriverhk.hkadbus2.activity;

import com.taxidriverhk.hkadbus2.exception.BadRequestException;
import com.taxidriverhk.hkadbus2.model.api.GetBusModelsResponse;
import com.taxidriverhk.hkadbus2.model.domain.BusModel;
import com.taxidriverhk.hkadbus2.service.BusService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.ws.rs.core.Response;
import java.util.Collections;

import static com.taxidriverhk.hkadbus2.util.MockDataHelper.LANGUAGE_EN;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetBusModelsActivityTest {

    @Mock
    private BusService busService;

    @Mock
    private BusModel busModel;

    @InjectMocks
    private GetBusModelsActivity activity;

    @Test
    public void GIVEN_validLanguage_WHEN_getBusModels_THEN_shouldReturnValidResponse() {
        when(busService.getBusModels(any())).thenReturn(Collections.singletonList(busModel));

        final Response response = activity.list(LANGUAGE_EN);

        verify(busService, times(1)).getBusModels(LANGUAGE_EN);
        final GetBusModelsResponse getBusModelsResponse = (GetBusModelsResponse) response.getEntity();
        assertThat(getBusModelsResponse.getBusModels(), containsInAnyOrder(busModel));
    }

    @Test
    public void GIVEN_invalidLanguage_WHEN_getBusModels_THEN_shouldThrowException() {
        assertThrows(BadRequestException.class, () -> activity.list("invalid-language"));
    }
}
