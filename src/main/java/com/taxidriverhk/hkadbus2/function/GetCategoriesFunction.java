package com.taxidriverhk.hkadbus2.function;

import com.google.common.annotations.VisibleForTesting;
import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import com.taxidriverhk.hkadbus2.component.CoreApiComponent;
import com.taxidriverhk.hkadbus2.component.impl.CoreApiComponentImpl;
import com.taxidriverhk.hkadbus2.model.api.GetCategoriesResponse;
import com.taxidriverhk.hkadbus2.model.domain.Category;
import com.taxidriverhk.hkadbus2.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Log4j2
@RequiredArgsConstructor
public class GetCategoriesFunction {

    private CoreApiComponent coreApiComponent;

    @FunctionName("get-categories")
    public HttpResponseMessage run(
            @HttpTrigger(
                name = "req",
                methods = {HttpMethod.GET},
                authLevel = AuthorizationLevel.ANONYMOUS)
            final HttpRequestMessage<Optional<String>> request,
            final ExecutionContext context
    ) {
        log.info("Getting categories.");
        final CategoryService categoryService = getCoreApiComponent().categoryService();
        final List<Category> categories = categoryService.getCategories();
        return request.createResponseBuilder(HttpStatus.OK)
                .body(GetCategoriesResponse.builder()
                        .categories(categories)
                        .build())
                .build();
    }

    @VisibleForTesting
    protected void setCoreApiComponent(final CoreApiComponent coreApiComponent) {
        this.coreApiComponent = coreApiComponent;
    }

    private CoreApiComponent getCoreApiComponent() {
        if (Objects.isNull(coreApiComponent)) {
            coreApiComponent = CoreApiComponentImpl.create();
        }
        return coreApiComponent;
    }
}
