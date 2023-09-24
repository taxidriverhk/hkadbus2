package com.taxidriverhk.hkadbus2.repository;

import java.util.List;
import java.util.Optional;

import com.taxidriverhk.hkadbus2.model.entity.UserEntity;

public interface UserRepository {

    List<UserEntity> getUsers();
    Optional<UserEntity> getUserByUsername(String username);
}
