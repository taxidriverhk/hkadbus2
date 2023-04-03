package com.taxidriverhk.hkadbus2.repository;

import java.util.Optional;

import com.taxidriverhk.hkadbus2.model.entity.UserEntity;

public interface UserRepository {

    Optional<UserEntity> getUserByUsername(String username);
}
