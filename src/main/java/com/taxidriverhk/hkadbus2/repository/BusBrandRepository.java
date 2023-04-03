package com.taxidriverhk.hkadbus2.repository;

import java.util.List;
import java.util.Optional;

import com.taxidriverhk.hkadbus2.model.entity.BusBrandEntity;

public interface BusBrandRepository {

    List<BusBrandEntity> getBusBrands();
    Optional<BusBrandEntity> getBusBrandByHashKey(String hashKey);
    String putBusBrand(BusBrandEntity busBrand);
}
