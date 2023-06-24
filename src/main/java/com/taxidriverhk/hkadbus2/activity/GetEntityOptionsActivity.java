package com.taxidriverhk.hkadbus2.activity;

import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.taxidriverhk.hkadbus2.model.api.GetEntityOptionsResponse;
import com.taxidriverhk.hkadbus2.model.domain.EntityOptionType;
import com.taxidriverhk.hkadbus2.provider.EntityOptionsProvider;

import lombok.RequiredArgsConstructor;

@Path("entities/{type}")
@RequiredArgsConstructor(onConstructor = @__({@Inject}))
public class GetEntityOptionsActivity {

    private final EntityOptionsProvider entityOptionsProvider;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(
            @PathParam("type") final String entityType,
            @QueryParam("language") final String language
    ) {
        final EntityOptionType entityOptionType = EntityOptionType.fromName(entityType);
        final Map<String, String> entityOptions = entityOptionsProvider.getEntityOptions(entityOptionType, language);
        final GetEntityOptionsResponse putPhotoResponse = GetEntityOptionsResponse.builder()
                .entityType(entityType)
                .options(entityOptions)
                .build();
        return Response.ok(putPhotoResponse).build();
    }
}
