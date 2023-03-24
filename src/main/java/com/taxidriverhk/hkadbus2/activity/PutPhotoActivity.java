package com.taxidriverhk.hkadbus2.activity;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.taxidriverhk.hkadbus2.model.api.PutPhotoRequest;
import com.taxidriverhk.hkadbus2.model.api.PutPhotoResponse;
import com.taxidriverhk.hkadbus2.service.PhotoService;

import lombok.RequiredArgsConstructor;

@Path("photo")
@RequiredArgsConstructor(onConstructor = @__({@Inject}))
public class PutPhotoActivity {

    private final PhotoService photoService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response put(final PutPhotoRequest request) {
        // TODO: add request validation
        final Long photoId = photoService.putPhoto(request);
        final PutPhotoResponse putPhotoResponse = PutPhotoResponse.builder()
                .photoId(photoId)
                .build();
        return Response.ok(putPhotoResponse).build();
    }
}
