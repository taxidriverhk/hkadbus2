package com.taxidriverhk.hkadbus2.repository.impl;

import com.taxidriverhk.hkadbus2.model.entity.BusModelEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static com.taxidriverhk.hkadbus2.util.MockDataHelper.BUS_BRAND_ENTITY_1;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.BUS_BRAND_ENTITY_2;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.BUS_MODEL_ENTITY_1;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.BUS_MODEL_ENTITY_2;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.BUS_MODEL_HASH_KEY_2;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;

public class BusModelSqlRepositoryTest extends SqlRepositoryTestBase {

    private BusModelSqlRepository sqlRepository;

    @BeforeEach
    public void setup() {
        sqlRepository = new BusModelSqlRepository(sessionFactory);
    }

    @Test
    public void WHEN_queryByBusBrandId_THEN_shouldReturnMatchingBusModels() {
        final List<BusModelEntity> busModelEntities = sqlRepository.getBusModels(BUS_BRAND_ENTITY_1.getId().toString());
        assertThat(busModelEntities, containsInAnyOrder(BUS_MODEL_ENTITY_1));
    }

    @Test
    public void WHEN_queryByHashKey_THEN_shouldReturnMatchingBusModel() {
        final Optional<BusModelEntity> busModelEntity = sqlRepository.getBusModelByHashKey(BUS_MODEL_HASH_KEY_2);
        assertThat(busModelEntity.get(), equalTo(BUS_MODEL_ENTITY_2));

        final Optional<BusModelEntity> nonExistentBusModelEntity = sqlRepository.getBusModelByHashKey("invalid-key");
        assertThat(nonExistentBusModelEntity.isPresent(), equalTo(false));
    }

    @Override
    protected void setupDataForTest(Session session) {
        final Transaction transaction = session.beginTransaction();
        session.save(BUS_BRAND_ENTITY_1);
        session.save(BUS_BRAND_ENTITY_2);
        session.save(BUS_MODEL_ENTITY_1);
        session.save(BUS_MODEL_ENTITY_2);
        transaction.commit();
    }
}
