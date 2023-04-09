package com.taxidriverhk.hkadbus2.repository.impl;

import static com.taxidriverhk.hkadbus2.util.MockDataHelper.BUS_ROUTE_ENTITY_1;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.BUS_ROUTE_ENTITY_2;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.BUS_ROUTE_ENTITY_3;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.LANGUAGE_EN;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.LANGUAGE_ZH;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.taxidriverhk.hkadbus2.model.domain.BusCompany;
import com.taxidriverhk.hkadbus2.model.entity.BusRouteEntity;

public class BusRouteSqlRepositoryTest extends SqlRepositoryTestBase {

    private BusRouteSqlRepository sqlRepository;

    @BeforeEach
    public void setup() {
        sqlRepository = new BusRouteSqlRepository(sessionFactory);
    }

    @Test
    public void WHEN_queryByHashKey_THEN_shouldReturnMatchingBusRoute() {
        final Optional<BusRouteEntity> busRouteEntity = sqlRepository.getBusRouteByHashKey("kmb-49x");
        assertThat(busRouteEntity.get(), equalTo(BUS_ROUTE_ENTITY_1));
    }

    @Test
    public void WHEN_queryByRouteNumber_THEN_shouldReturnMatchingBusRoutes() {
        final List<BusRouteEntity> busRouteEntities = sqlRepository.getBusRoutes("2");
        assertThat(busRouteEntities, containsInAnyOrder(BUS_ROUTE_ENTITY_2, BUS_ROUTE_ENTITY_3));
    }

    @Test
    public void WHEN_insertNewEntity_THEN_shouldReturnBusRouteId() {
        final String busRouteEntityId = sqlRepository.putBusRoute(BusRouteEntity.builder()
                .routeNumber("4")
                .hashKey("nwfb-4")
                .busCompanies(Lists.newArrayList(BusCompany.NWFB.getText()))
                .startLocation(ImmutableMap.of(
                        LANGUAGE_EN, "Wah Fu",
                        LANGUAGE_ZH, "華富"))
                .endLocation(ImmutableMap.of(
                        LANGUAGE_EN, "Central",
                        LANGUAGE_ZH, "中環"))
                .build());
        assertThat(busRouteEntityId, notNullValue());

        final Optional<BusRouteEntity> busRouteEntity = sqlRepository.getBusRouteByHashKey("nwfb-4");
        assertThat(busRouteEntity.isPresent(), equalTo(true));
    }

    @Override
    protected void setupDataForTest(Session session) {
        final Transaction transaction = session.beginTransaction();
        session.save(BUS_ROUTE_ENTITY_1);
        session.save(BUS_ROUTE_ENTITY_2);
        session.save(BUS_ROUTE_ENTITY_3);
        transaction.commit();
    }
}
