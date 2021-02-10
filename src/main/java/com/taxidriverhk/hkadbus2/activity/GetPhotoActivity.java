package com.taxidriverhk.hkadbus2.activity;

import com.taxidriverhk.hkadbus2.model.api.GetPhotoResponse;
import com.taxidriverhk.hkadbus2.model.domain.Photo;
import com.taxidriverhk.hkadbus2.service.PhotoService;
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

@Path("photos/{photoId}")
@RequiredArgsConstructor(onConstructor = @__({@Inject}))
public class GetPhotoActivity {

    private final PhotoService photoService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(
            @PathParam("photoId") final Long photoId,
            @QueryParam("language") final String language
    ) {
        RequestValidator.validateLanguage(language);
        final Photo photo = photoService.getPhoto(photoId, language);
        final GetPhotoResponse getPhotoResponse = GetPhotoResponse.builder()
                .photo(photo)
                .build();
        return Response.ok(getPhotoResponse).build();
    }
}
