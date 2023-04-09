package com.taxidriverhk.hkadbus2.repository.impl;

import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.taxidriverhk.hkadbus2.model.entity.BusRouteEntity;
import com.taxidriverhk.hkadbus2.model.entity.EntityConstants;
import com.taxidriverhk.hkadbus2.repository.BusRouteRepository;
import com.taxidriverhk.hkadbus2.util.SqlQueryUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Log4j2
public class BusRouteSqlRepository implements BusRouteRepository {

    private final SessionFactory sessionFactory;

    @Override
    public Optional<BusRouteEntity> getBusRouteByHashKey(final String hashKey) {
        log.info("Querying bus route by hash key {}", hashKey);

        final Session session = sessionFactory.openSession();
        final Optional<BusRouteEntity> result = SqlQueryUtil.selectSingleItemByCompositeKey(
                session,
                BusRouteEntity.class,
                EntityConstants.HASH_KEY_ATTRIBUTE,
                hashKey);
        session.close();
        return result;
    }

    @Override
    public List<BusRouteEntity> getBusRoutes(final String routeNumber) {
        log.info("Querying bus routes by route number {}", routeNumber);

        final Session session = sessionFactory.openSession();
        final List<BusRouteEntity> results = SqlQueryUtil.selectMatchingItemsByCompositeKey(
                session,
                BusRouteEntity.class,
                "routeNumber",
                routeNumber);
        session.close();
        return results;
    }

    @Override
    public String putBusRoute(final BusRouteEntity busRoute) {
        log.info("Inserting bus routes with entity {}", busRoute);

        final Session session = sessionFactory.openSession();
        final BusRouteEntity result = SqlQueryUtil.mutateWithTransaction(
                session, 
                BusRouteEntity.class,
                () -> SqlQueryUtil.insertEntity(
                        session,
                        BusRouteEntity.class,
                        busRoute
                ));
        session.close();
        return result.getId().toString();
    }
}
