package com.taxidriverhk.hkadbus2.repository;

import java.util.List;
import java.util.Optional;

import com.taxidriverhk.hkadbus2.model.entity.BusModelEntity;

public interface BusModelRepository {

    List<BusModelEntity> getBusModels(String brandId);
    Optional<BusModelEntity> getBusModelByHashKey(String hashKey);
    String putBusModel(BusModelEntity busModel);
}
