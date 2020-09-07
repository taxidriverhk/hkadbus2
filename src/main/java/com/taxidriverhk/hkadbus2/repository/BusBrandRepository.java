package com.taxidriverhk.hkadbus2.repository;

import com.taxidriverhk.hkadbus2.model.entity.BusBrandEntity;

import java.util.List;
import java.util.Optional;

public interface BusBrandRepository {

    List<BusBrandEntity> getBusBrands();
    Optional<BusBrandEntity> getBusBrandByHashKey(String hashKey);
}
