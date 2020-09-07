package com.taxidriverhk.hkadbus2.repository;

import com.taxidriverhk.hkadbus2.model.entity.BusModelEntity;

import java.util.List;
import java.util.Optional;

public interface BusModelRepository {

    List<BusModelEntity> getBusModelsByBrandId(String brandId);
    Optional<BusModelEntity> getBusModelByHashKey(String hashKey);
}
