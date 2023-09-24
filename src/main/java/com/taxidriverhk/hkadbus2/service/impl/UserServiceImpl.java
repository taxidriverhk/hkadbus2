package com.taxidriverhk.hkadbus2.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import com.taxidriverhk.hkadbus2.mapper.EntityMapper;
import com.taxidriverhk.hkadbus2.model.domain.User;
import com.taxidriverhk.hkadbus2.model.entity.UserEntity;
import com.taxidriverhk.hkadbus2.repository.UserRepository;
import com.taxidriverhk.hkadbus2.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor(onConstructor = @__({ @Inject }))
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<User> getUsers() {
        List<UserEntity> userEntities = userRepository.getUsers();
        log.info("Obtained {} users", userEntities.size());
        return userEntities.stream()
                .map(userEntity -> EntityMapper.INSTANCE.userEntityToUser(userEntity))
                .collect(Collectors.toList());
    }
}
