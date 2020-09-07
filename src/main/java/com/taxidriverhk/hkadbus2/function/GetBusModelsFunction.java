package com.taxidriverhk.hkadbus2.function;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import com.taxidriverhk.hkadbus2.model.api.GetBusModelsRequest;
import com.taxidriverhk.hkadbus2.model.api.GetBusModelsResponse;
import com.taxidriverhk.hkadbus2.model.domain.BusModel;
import com.taxidriverhk.hkadbus2.service.BusService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
public class GetBusModelsFunction extends CoreApiFunctionBase<GetBusModelsRequest, GetBusModelsResponse> {

    @FunctionName("get-bus-models")
    public HttpResponseMessage run(
            @HttpTrigger(
                    name = "req",
                    methods = {HttpMethod.POST},
                    authLevel = AuthorizationLevel.ANONYMOUS)
            final HttpRequestMessage<GetBusModelsRequest> request,
            final ExecutionContext context
    ) {
        return executeFunction(request, (getBusModelsRequest) -> {
            final String language = getBusModelsRequest.getLanguage();
            log.info("Getting all bus models with language {}.", language);

            final BusService busService = getCoreApiComponent().busService();
            final List<BusModel> busModels = busService.getBusModels(language);

            log.info("Returning with {} bus models.", busModels.size());
            return GetBusModelsResponse.builder()
                    .busModels(busModels)
                    .build();
        });
    }
}
