package com.taxidriverhk.hkadbus2.service.impl;

import static com.taxidriverhk.hkadbus2.util.MockDataHelper.USER_ENTITY_1;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.google.common.collect.Lists;
import com.taxidriverhk.hkadbus2.model.domain.User;
import com.taxidriverhk.hkadbus2.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
    
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void WHEN_getUsers_THEN_shouldReturnUsersWithUsernames() {
        when(userRepository.getUsers()).thenReturn(Lists.newArrayList(USER_ENTITY_1));
        final List<User> users = userService.getUsers();
        assertThat(users.get(0).getUsername(), equalTo(USER_ENTITY_1.getUsername()));
    }
}
