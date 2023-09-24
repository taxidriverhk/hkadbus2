package com.taxidriverhk.hkadbus2.activity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;

import javax.ws.rs.core.Response;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.google.common.collect.Lists;
import com.taxidriverhk.hkadbus2.model.api.GetUsersResponse;
import com.taxidriverhk.hkadbus2.model.domain.User;
import com.taxidriverhk.hkadbus2.service.UserService;

@ExtendWith(MockitoExtension.class)
public class GetUsersActivityTest {
    
    @Mock
    private UserService userService;

    @Mock
    private User user;

    @InjectMocks
    private GetUsersActivity activity;

    @Test
    public void WHEN_getUsers_THEN_shouldReturnValidResponse() {
        when(userService.getUsers()).thenReturn(Lists.newArrayList(user));

        final Response response = activity.list();
        final GetUsersResponse getUsersResponse = (GetUsersResponse) response.getEntity();

        assertThat(getUsersResponse.getUsers().get(0), equalTo(user));
    }
}
