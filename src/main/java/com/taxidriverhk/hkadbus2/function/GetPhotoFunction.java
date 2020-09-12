package com.taxidriverhk.hkadbus2.function;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import com.taxidriverhk.hkadbus2.model.api.GetPhotoRequest;
import com.taxidriverhk.hkadbus2.model.api.GetPhotoResponse;
import com.taxidriverhk.hkadbus2.model.domain.Photo;
import com.taxidriverhk.hkadbus2.service.PhotoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
public class GetPhotoFunction extends CoreApiFunctionBase<GetPhotoRequest, GetPhotoResponse> {

    @FunctionName("get-photo")
    public HttpResponseMessage run(
            @HttpTrigger(
                    name = "req",
                    methods = {HttpMethod.POST},
                    authLevel = AuthorizationLevel.ANONYMOUS)
            final HttpRequestMessage<GetPhotoRequest> request,
            final ExecutionContext context
    ) {
        return executeFunction(request, (getPhotoRequest) -> {
            final PhotoService photoService = getCoreApiComponent().photoService();
            final String photoId = getPhotoRequest.getPhotoId();
            final String language = getPhotoRequest.getLanguage();

            final Photo photo = photoService.getPhoto(photoId, language);
            return GetPhotoResponse.builder()
                    .photo(photo)
                    .build();
        });
    }
}
