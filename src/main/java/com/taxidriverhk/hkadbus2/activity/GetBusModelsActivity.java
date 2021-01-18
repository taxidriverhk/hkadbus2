package com.taxidriverhk.hkadbus2.activity;

import com.taxidriverhk.hkadbus2.model.api.GetBusModelsResponse;
import com.taxidriverhk.hkadbus2.model.domain.BusModel;
import com.taxidriverhk.hkadbus2.service.BusService;
import com.taxidriverhk.hkadbus2.util.RequestValidator;
import lombok.RequiredArgsConstructor;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path("bus-models")
@RequiredArgsConstructor(onConstructor = @__({@Inject}))
public class GetBusModelsActivity {

    private final BusService busService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response list(@QueryParam("language") final String language) {
        RequestValidator.validateLanguage(language);
        final List<BusModel> busModels = busService.getBusModels(language);
        final GetBusModelsResponse getBusModelsResponse = GetBusModelsResponse.builder()
                .busModels(busModels)
                .build();
        return Response.ok(getBusModelsResponse).build();
    }
}
