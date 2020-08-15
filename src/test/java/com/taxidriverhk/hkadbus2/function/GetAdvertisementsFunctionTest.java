package com.taxidriverhk.hkadbus2.function;

import com.google.common.collect.Lists;
import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.taxidriverhk.hkadbus2.component.CoreApiComponent;
import com.taxidriverhk.hkadbus2.model.api.GetAdvertisementsRequest;
import com.taxidriverhk.hkadbus2.model.api.GetAdvertisementsResponse;
import com.taxidriverhk.hkadbus2.service.AdvertisementService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.taxidriverhk.hkadbus2.util.MockDataHelper.ADVERTISEMENT_1;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.LANGUAGE_EN;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetAdvertisementsFunctionTest extends FunctionTestBase<GetAdvertisementsRequest> {

    @Mock
    private ExecutionContext context;

    @Mock
    private CoreApiComponent coreApiComponent;

    @Mock
    private AdvertisementService advertisementService;

    @Mock
    private GetAdvertisementsRequest getAdvertisementsRequest;

    @InjectMocks
    private GetAdvertisementsFunction function;

    @BeforeEach
    public void setup() {
        when(coreApiComponent.advertisementService()).thenReturn(advertisementService);
        function.setCoreApiComponent(coreApiComponent);
    }

    @Test
    public void GIVEN_requestBody_WHEN_getAdvertisements_THEN_returnListOfAdvertisements() {
        when(getAdvertisementsRequest.getCategoryId()).thenReturn("test-category-id");
        when(getAdvertisementsRequest.getLanguage()).thenReturn(LANGUAGE_EN);
        when(request.getBody()).thenReturn(getAdvertisementsRequest);
        when(advertisementService.getAdvertisements(anyString(), anyString())).thenReturn(Lists.newArrayList(ADVERTISEMENT_1));

        final HttpResponseMessage responseMessage = function.run(request, context);
        final GetAdvertisementsResponse response = (GetAdvertisementsResponse) responseMessage.getBody();

        verify(advertisementService, times(1)).getAdvertisements("test-category-id", LANGUAGE_EN);
        assertThat(response.getAdvertisements(), equalTo(Lists.newArrayList(ADVERTISEMENT_1)));
    }
}
