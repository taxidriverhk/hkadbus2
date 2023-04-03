package com.taxidriverhk.hkadbus2.repository;

import java.util.List;
import java.util.Optional;

import com.taxidriverhk.hkadbus2.model.entity.BusEntity;

public interface BusRepository {

    Optional<BusEntity> getBus(String busId);
    List<BusEntity> getBuses(String busModelId);
    String putBus(BusEntity bus);
}
