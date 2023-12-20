package com.taxidriverhk.hkadbus2.activity;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.taxidriverhk.hkadbus2.model.api.SearchPhotosResponse;
import com.taxidriverhk.hkadbus2.model.domain.SearchPhotoFilter;
import com.taxidriverhk.hkadbus2.model.domain.SearchPhotoResult;
import com.taxidriverhk.hkadbus2.service.PhotoService;
import com.taxidriverhk.hkadbus2.util.RequestValidator;

import lombok.RequiredArgsConstructor;

@Path("photos")
@RequiredArgsConstructor(onConstructor = @__({@Inject}))
public class SearchPhotosActivity {

    private final PhotoService photoService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response search(
            @QueryParam("search_text") final String query,
            @QueryParam("order_by") final String orderBy,
            @QueryParam("sort") final String sort,
            @QueryParam("size") final Integer size,
            @QueryParam("next_sort_key") final String nextSortKey,
            @QueryParam("language") final String language,
            @QueryParam("category_name") final List<String> categoryNames,
            @QueryParam("category_id") final List<String> categoryIds,
            @QueryParam("advertisement_name") final List<String> advertisementNames,
            @QueryParam("advertisement_id") final List<String> advertisementIds,
            @QueryParam("bus_company_name") final List<String> busCompanyNames,
            @QueryParam("bus_model_name") final List<String> busModelNames,
            @QueryParam("bus_model_id") final List<String> busModelIds,
            @QueryParam("bus_route_number") final List<String> busRouteNumbers,
            @QueryParam("bus_route_id") final List<String> busRouteIds,
            @QueryParam("fleet_prefix") final List<String> fleetPrefixes,
            @QueryParam("fleet_number") final List<String> fleetNumbers,
            @QueryParam("license_plate_number") final List<String> licensePlateNumbers,
            @QueryParam("uploader_name") final List<String> uploaderNames
    ) {
        RequestValidator.validateLanguage(language);
        RequestValidator.validateOrderBy(orderBy);
        RequestValidator.validateSort(sort);
        RequestValidator.validateSize(size);

        final SearchPhotoFilter searchPhotoFilter = SearchPhotoFilter.builder()
                .advertisementIds(advertisementIds)
                .advertisementNames(advertisementNames)
                .categoryIds(categoryIds)
                .categoryNames(categoryNames)
                .busCompanyNames(busCompanyNames)
                .busModelNames(busModelNames)
                .busModelIds(busModelIds)
                .busRouteNumbers(busRouteNumbers)
                .busRouteIds(busRouteIds)
                .fleetPrefixes(fleetPrefixes)
                .fleetNumbers(fleetNumbers)
                .licensePlateNumbers(licensePlateNumbers)
                .uploaderNames(uploaderNames)
                .language(language)
                .build();
        final SearchPhotoResult searchPhotoResult = photoService.searchPhotos(
                query,
                orderBy,
                sort,
                searchPhotoFilter,
                size,
                nextSortKey);

        final SearchPhotosResponse searchPhotosResponse = SearchPhotosResponse.builder()
                .total(searchPhotoResult.getTotal())
                .results(searchPhotoResult.getResults())
                .nextPageCursor(searchPhotoResult.getNextPageCursor())
                .build();
        return Response.ok(searchPhotosResponse)
                .build();
    }
}
