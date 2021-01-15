package com.taxidriverhk.hkadbus2.activity;

import com.taxidriverhk.hkadbus2.model.api.GetCategoriesResponse;
import com.taxidriverhk.hkadbus2.model.domain.Category;
import com.taxidriverhk.hkadbus2.service.CategoryService;
import lombok.RequiredArgsConstructor;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("categories")
@RequiredArgsConstructor(onConstructor = @__({@Inject}))
public class GetCategoriesActivity {

    private final CategoryService categoryService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response list(@QueryParam("language") final String language) {
        final List<Category> categories = categoryService.getCategories(language);
        final GetCategoriesResponse getCategoriesResponse = GetCategoriesResponse.builder()
                .categories(categories)
                .build();
        return Response.ok(getCategoriesResponse).build();
    }
}
