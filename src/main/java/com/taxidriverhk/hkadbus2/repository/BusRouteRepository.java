package com.taxidriverhk.hkadbus2.repository;

import java.util.List;
import java.util.Optional;

import com.taxidriverhk.hkadbus2.model.entity.BusRouteEntity;

public interface BusRouteRepository {

    Optional<BusRouteEntity> getBusRouteByHashKey(String hashKey);
    List<BusRouteEntity> getBusRoutes(String routeNumber);
    String putBusRoute(BusRouteEntity busRoute);
}
