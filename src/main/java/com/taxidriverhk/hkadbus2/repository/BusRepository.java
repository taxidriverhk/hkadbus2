package com.taxidriverhk.hkadbus2.repository;

import com.taxidriverhk.hkadbus2.model.entity.BusEntity;

import java.util.List;
import java.util.Optional;

public interface BusRepository {

    Optional<BusEntity> getBus(String busId);
    List<BusEntity> getBuses(String busModelId);
}
