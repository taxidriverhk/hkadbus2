package com.taxidriverhk.hkadbus2.activity;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.taxidriverhk.hkadbus2.model.api.GetUsersResponse;
import com.taxidriverhk.hkadbus2.model.domain.User;
import com.taxidriverhk.hkadbus2.service.UserService;

import lombok.RequiredArgsConstructor;

@Path("users")
@RequiredArgsConstructor(onConstructor = @__({@Inject}))
public class GetUsersActivity {

    private final UserService userService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response list() {
        final List<User> users = userService.getUsers();
        final GetUsersResponse getUsersResponse = GetUsersResponse.builder()
                .users(users)
                .build();
        return Response.ok(getUsersResponse).build();
    }
}
