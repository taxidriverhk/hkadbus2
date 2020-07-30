package com.taxidriverhk.hkadbus2.function;

import com.google.common.collect.Lists;
import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import com.taxidriverhk.hkadbus2.model.api.GetCategoriesResponse;
import com.taxidriverhk.hkadbus2.model.domain.Category;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.Optional;

@Log4j2
@RequiredArgsConstructor
public class GetCategoriesFunction {

    @FunctionName("get-categories")
    public HttpResponseMessage run(
            @HttpTrigger(
                name = "req",
                methods = {HttpMethod.GET, HttpMethod.POST},
                authLevel = AuthorizationLevel.ANONYMOUS)
            final HttpRequestMessage<Optional<String>> request,
            final ExecutionContext context
    ) {
        log.info("Getting categories.");
        return request.createResponseBuilder(HttpStatus.OK)
                .body(GetCategoriesResponse.builder()
                        .categories(Lists.newArrayList(
                                Category.builder()
                                        .id("1")
                                        .name("Test Category")
                                        .thumbnail("http://thunbnail.jpg")
                                        .build()))
                        .build())
                .build();
    }
}
