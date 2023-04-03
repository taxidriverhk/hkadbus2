package com.taxidriverhk.hkadbus2.repository.impl;

import java.util.List;
import java.util.Optional;

import org.hibernate.SessionFactory;

import com.taxidriverhk.hkadbus2.model.entity.BusRouteEntity;
import com.taxidriverhk.hkadbus2.repository.BusRouteRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Log4j2
public class BusRoutelSqlRepository implements BusRouteRepository {

    private final SessionFactory sessionFactory;

    @Override
    public Optional<BusRouteEntity> getBusRouteByHashKey(String hashKey) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBusRouteByHashKey'");
    }

    @Override
    public List<BusRouteEntity> getBusRoutes(String routeNumber) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBusRoutes'");
    }

    @Override
    public String putBusRoute(BusRouteEntity busRoute) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'putBusRoute'");
    }
    
}
