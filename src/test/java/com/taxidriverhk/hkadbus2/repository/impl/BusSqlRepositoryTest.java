package com.taxidriverhk.hkadbus2.repository.impl;

import static com.taxidriverhk.hkadbus2.util.MockDataHelper.BUS_BRAND_ENTITY_1;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.BUS_ENTITY_1;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.BUS_MODEL_ENTITY_1;
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

import com.taxidriverhk.hkadbus2.model.domain.BusCompany;
import com.taxidriverhk.hkadbus2.model.entity.BusEntity;

public class BusSqlRepositoryTest extends SqlRepositoryTestBase {

    private BusSqlRepository sqlRepository;

    @BeforeEach
    public void setup() {
        sqlRepository = new BusSqlRepository(sessionFactory);
    }

    @Test
    public void WHEN_queryById_THEN_shouldReturnMatchingBus() {
        final Optional<BusEntity> busEntity = sqlRepository.getBus(BUS_ENTITY_1.getId().toString());
        assertThat(busEntity.get(), equalTo(BUS_ENTITY_1));
    }

    @Test
    public void WHEN_queryByModelId_THEN_shouldReturnMatchingBuses() {
        final List<BusEntity> busEntities = sqlRepository.getBuses(BUS_MODEL_ENTITY_1.getId().toString());
        assertThat(busEntities, containsInAnyOrder(BUS_ENTITY_1));
    }

    @Test
    public void WHEN_insertNewEntity_THEN_shouldReturnBusId() {
        final String busEntityId = sqlRepository.putBus(BusEntity.builder()
                .shortId(8L)
                .busCompany(BusCompany.KMB.getText())
                .fleetPrefix("AD")
                .fleetNumber("16")
                .licensePlateNumber("FX4567")
                .busModel(BUS_MODEL_ENTITY_1)
                .build());
        assertThat(busEntityId, notNullValue());

        final Optional<BusEntity> busEntity = sqlRepository.getBus(busEntityId);
        assertThat(busEntity.isPresent(), equalTo(true));
    }

    @Override
    protected void setupDataForTest(Session session) {
        final Transaction transaction = session.beginTransaction();
        session.save(BUS_BRAND_ENTITY_1);
        session.save(BUS_MODEL_ENTITY_1);
        session.save(BUS_ENTITY_1);
        transaction.commit();
    }
}
