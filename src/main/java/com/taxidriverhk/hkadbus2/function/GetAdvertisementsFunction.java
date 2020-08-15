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
import com.taxidriverhk.hkadbus2.component.DaggerCoreApiComponent;
import com.taxidriverhk.hkadbus2.model.api.GetAdvertisementsRequest;
import com.taxidriverhk.hkadbus2.model.api.GetAdvertisementsResponse;
import com.taxidriverhk.hkadbus2.model.domain.Advertisement;
import com.taxidriverhk.hkadbus2.service.AdvertisementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.Objects;

@Log4j2
@RequiredArgsConstructor
public class GetAdvertisementsFunction {

    private CoreApiComponent coreApiComponent;

    @FunctionName("get-advertisements")
    public HttpResponseMessage run(
            @HttpTrigger(
                name = "req",
                methods = {HttpMethod.POST},
                authLevel = AuthorizationLevel.ANONYMOUS)
            final HttpRequestMessage<GetAdvertisementsRequest> request,
            final ExecutionContext context
    ) {
        final String categoryId = request.getBody().getCategoryId();
        final String language = request.getBody().getLanguage();
        log.info("Getting all advertisements under category hash key {} and language {}.", categoryId, language);

        final AdvertisementService advertisementService = getCoreApiComponent().advertisementService();
        final List<Advertisement> advertisements = advertisementService.getAdvertisements(categoryId, language);
        return request.createResponseBuilder(HttpStatus.OK)
                .body(GetAdvertisementsResponse.builder()
                        .advertisements(advertisements)
                        .build())
                .build();
    }

    @VisibleForTesting
    protected void setCoreApiComponent(final CoreApiComponent coreApiComponent) {
        this.coreApiComponent = coreApiComponent;
    }

    private CoreApiComponent getCoreApiComponent() {
        if (Objects.isNull(coreApiComponent)) {
            coreApiComponent = DaggerCoreApiComponent.builder().build();
        }
        return coreApiComponent;
    }
}
