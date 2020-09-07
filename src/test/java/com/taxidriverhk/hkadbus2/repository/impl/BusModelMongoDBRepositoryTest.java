package com.taxidriverhk.hkadbus2.repository.impl;

import com.mongodb.client.MongoCollection;
import com.taxidriverhk.hkadbus2.model.entity.BusModelEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.taxidriverhk.hkadbus2.util.MockDataHelper.BUS_BRAND_HASH_KEY_1;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.BUS_MODEL_ENTITY_1;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.BUS_MODEL_ENTITY_2;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

public class BusModelMongoDBRepositoryTest extends MongoDBRepositoryTestBase<BusModelEntity> {

    private BusModelMongoDBRepository busModelMongoDBRepository;

    public BusModelMongoDBRepositoryTest() {
        super(BusModelEntity.class, "bus-models");
    }

    @BeforeEach
    public void setup() {
        busModelMongoDBRepository = new BusModelMongoDBRepository(mongoDatabase);
    }

    @Test
    public void WHEN_getBusModelsByBrandId_THEN_shouldFindAllMatchingBusModelEntities() {
        final List<BusModelEntity> busModelEntities = busModelMongoDBRepository.getBusModelsByBrandId(BUS_BRAND_HASH_KEY_1);
        assertThat(busModelEntities, containsInAnyOrder(BUS_MODEL_ENTITY_1));
    }

    @Override
    protected void setupDataForTests(MongoCollection<BusModelEntity> mongoCollection) {
        mongoCollection.insertOne(BUS_MODEL_ENTITY_1);
        mongoCollection.insertOne(BUS_MODEL_ENTITY_2);
    }
}
