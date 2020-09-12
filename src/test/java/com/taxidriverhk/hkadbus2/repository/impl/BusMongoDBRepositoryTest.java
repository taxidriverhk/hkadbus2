package com.taxidriverhk.hkadbus2.repository.impl;

import com.mongodb.client.MongoCollection;
import com.taxidriverhk.hkadbus2.model.entity.BusEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.taxidriverhk.hkadbus2.util.MockDataHelper.BUS_ENTITY_1;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.BUS_ENTITY_2;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.BUS_ID_1;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.BUS_MODEL_HASH_KEY_2;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;

public class BusMongoDBRepositoryTest extends MongoDBRepositoryTestBase<BusEntity> {

    private BusMongoDBRepository busMongoDBRepository;

    public BusMongoDBRepositoryTest() {
        super(BusEntity.class, "buses");
    }

    @BeforeEach
    public void setup() {
        busMongoDBRepository = new BusMongoDBRepository(mongoDatabase);
    }

    @Test
    public void WHEN_queryBusByValidId_THEN_shouldReturnMatchingEntity() {
        assertThat(busMongoDBRepository.getBus(BUS_ID_1), equalTo(Optional.of(BUS_ENTITY_1)));
    }

    @Test
    public void WHEN_queryBusByInvalidId_THEN_shouldReturnNothing() {
        assertThat(busMongoDBRepository.getBus("invalid"), equalTo(Optional.empty()));
    }

    @Test
    public void WHEN_queryBusesByModelId_THEN_shouldReturnMatchingBusEntities() {
        assertThat(busMongoDBRepository.getBuses(BUS_MODEL_HASH_KEY_2), containsInAnyOrder(BUS_ENTITY_2));
    }

    @Override
    protected void setupDataForTests(MongoCollection<BusEntity> mongoCollection) {
        mongoCollection.insertOne(BUS_ENTITY_1);
        mongoCollection.insertOne(BUS_ENTITY_2);
    }
}
