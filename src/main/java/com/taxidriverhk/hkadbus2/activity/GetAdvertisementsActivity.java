package com.taxidriverhk.hkadbus2.activity;

import com.taxidriverhk.hkadbus2.exception.BadRequestException;
import com.taxidriverhk.hkadbus2.model.api.GetAdvertisementsResponse;
import com.taxidriverhk.hkadbus2.model.domain.Advertisement;
import com.taxidriverhk.hkadbus2.service.AdvertisementService;
import com.taxidriverhk.hkadbus2.util.RequestValidator;
import lombok.RequiredArgsConstructor;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("categories/{categoryId}/advertisements")
@RequiredArgsConstructor(onConstructor = @__({@Inject}))
public class GetAdvertisementsActivity {

    private final AdvertisementService advertisementService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response list(
            @PathParam("categoryId") final String categoryId,
            @QueryParam("language") final String language
    ) {
        RequestValidator.validateLanguage(language);
        final List<Advertisement> advertisements = advertisementService.getAdvertisements(categoryId, language);
        final GetAdvertisementsResponse getAdvertisementsResponse = GetAdvertisementsResponse.builder()
                .advertisements(advertisements)
                .build();
        return Response.ok(getAdvertisementsResponse).build();
    }
}
