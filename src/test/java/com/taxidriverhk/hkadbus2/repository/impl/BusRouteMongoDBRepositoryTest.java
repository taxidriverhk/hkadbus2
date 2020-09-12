package com.taxidriverhk.hkadbus2.repository.impl;

import com.mongodb.client.MongoCollection;
import com.taxidriverhk.hkadbus2.model.entity.BusRouteEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.taxidriverhk.hkadbus2.util.MockDataHelper.BUS_ROUTE_ENTITY_1;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.BUS_ROUTE_ENTITY_2;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.BUS_ROUTE_ENTITY_3;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.BUS_ROUTE_HASH_KEY_1;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;

public class BusRouteMongoDBRepositoryTest extends MongoDBRepositoryTestBase<BusRouteEntity> {

    private BusRouteMongoDBRepository busRouteMongoDBRepository;

    public BusRouteMongoDBRepositoryTest() {
        super(BusRouteEntity.class, "bus-routes");
    }

    @BeforeEach
    public void setup() {
        busRouteMongoDBRepository = new BusRouteMongoDBRepository(mongoDatabase);
    }

    @Test
    public void WHEN_queryBusRouteByValidHashKey_THEN_shouldReturnMatchingEntity() {
        assertThat(busRouteMongoDBRepository.getBusRouteByHashKey(BUS_ROUTE_HASH_KEY_1), equalTo(Optional.of(BUS_ROUTE_ENTITY_1)));
    }

    @Test
    public void WHEN_queryBusRouteByInvalidHashKey_THEN_shouldReturnNothing() {
        assertThat(busRouteMongoDBRepository.getBusRouteByHashKey("invalid"), equalTo(Optional.empty()));
    }

    @Test
    public void WHEN_queryBusRoutesByHashKey_THEN_shouldReturnMatchingEntities() {
        assertThat(busRouteMongoDBRepository.getBusRoutes("2"), containsInAnyOrder(BUS_ROUTE_ENTITY_2, BUS_ROUTE_ENTITY_3));
    }

    @Override
    protected void setupDataForTests(MongoCollection<BusRouteEntity> mongoCollection) {
        mongoCollection.insertOne(BUS_ROUTE_ENTITY_1);
        mongoCollection.insertOne(BUS_ROUTE_ENTITY_2);
        mongoCollection.insertOne(BUS_ROUTE_ENTITY_3);
    }
}
