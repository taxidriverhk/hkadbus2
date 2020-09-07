package com.taxidriverhk.hkadbus2.function;

import com.google.common.collect.Lists;
import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.taxidriverhk.hkadbus2.component.CoreApiComponent;
import com.taxidriverhk.hkadbus2.model.api.GetBusModelsRequest;
import com.taxidriverhk.hkadbus2.model.api.GetBusModelsResponse;
import com.taxidriverhk.hkadbus2.service.BusService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.taxidriverhk.hkadbus2.util.MockDataHelper.BUS_MODEL_1;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.LANGUAGE_EN;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetBusModelsFunctionTest extends FunctionTestBase<GetBusModelsRequest> {

    @Mock
    private ExecutionContext context;

    @Mock
    private CoreApiComponent coreApiComponent;

    @Mock
    private BusService busService;

    @Mock
    private GetBusModelsRequest getBusModelsRequest;

    @InjectMocks
    private GetBusModelsFunction function;

    @BeforeEach
    public void setup() {
        when(coreApiComponent.busService()).thenReturn(busService);
        function.setCoreApiComponent(coreApiComponent);
    }

    @Test
    public void GIVEN_requestBody_WHEN_getBusModels_THEN_returnListOfBusModels() {
        when(getBusModelsRequest.getLanguage()).thenReturn(LANGUAGE_EN);
        when(request.getBody()).thenReturn(getBusModelsRequest);
        when(busService.getBusModels(anyString())).thenReturn(Lists.newArrayList(BUS_MODEL_1));

        final HttpResponseMessage responseMessage = function.run(request, context);
        final GetBusModelsResponse response = (GetBusModelsResponse) responseMessage.getBody();

        verify(busService, times(1)).getBusModels(LANGUAGE_EN);
        assertThat(response.getBusModels(), equalTo(Lists.newArrayList(BUS_MODEL_1)));
    }
}
