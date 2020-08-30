package com.taxidriverhk.hkadbus2.function;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import com.taxidriverhk.hkadbus2.model.api.SearchPhotosRequest;
import com.taxidriverhk.hkadbus2.model.api.SearchPhotosResponse;
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
        return request.createResponseBuilder(HttpStatus.OK)
                .body(SearchPhotosResponse.builder()
                        .build())
                .build();
    }
}
