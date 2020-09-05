package com.taxidriverhk.hkadbus2.function;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import com.taxidriverhk.hkadbus2.model.api.SearchPhotosRequest;
import com.taxidriverhk.hkadbus2.model.api.SearchPhotosResponse;
import com.taxidriverhk.hkadbus2.service.PhotoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
public class SearchPhotosFunction extends CoreApiFunctionBase<SearchPhotosRequest, SearchPhotosResponse> {

    @FunctionName("search-photos")
    public HttpResponseMessage run(
            @HttpTrigger(
                    name = "req",
                    methods = {HttpMethod.POST},
                    authLevel = AuthorizationLevel.ANONYMOUS)
            final HttpRequestMessage<SearchPhotosRequest> request,
            final ExecutionContext context
    ) {
        return executeFunction(request, (searchPhotosRequest) -> {
            final PhotoService photoService = getCoreApiComponent().photoService();
            return SearchPhotosResponse.builder()
                   .build();
        });
    }
}
