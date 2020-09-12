package com.taxidriverhk.hkadbus2.repository;

import com.taxidriverhk.hkadbus2.model.entity.BusRouteEntity;

import java.util.List;
import java.util.Optional;

public interface BusRouteRepository {

    Optional<BusRouteEntity> getBusRouteByHashKey(String hashKey);
    List<BusRouteEntity> getBusRoutes(String routeNumber);
}
