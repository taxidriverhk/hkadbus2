package com.taxidriverhk.hkadbus2.repository.impl;

import static com.taxidriverhk.hkadbus2.util.MockDataHelper.BUS_BRAND_ENTITY_1;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.BUS_BRAND_ENTITY_2;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.BUS_BRAND_HASH_KEY_1;
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
import com.taxidriverhk.hkadbus2.model.entity.BusBrandEntity;

public class BusBrandSqlRepositoryTest extends SqlRepositoryTestBase {

    private BusBrandSqlRepository sqlRepository;

    @BeforeEach
    public void setup() {
        sqlRepository = new BusBrandSqlRepository(sessionFactory);
    }

    @Test
    public void WHEN_queryWithoutFilter_THEN_shouldReturnAllBusBrands() {
        final List<BusBrandEntity> busBrandEntities = sqlRepository.getBusBrands();
        assertThat(busBrandEntities, containsInAnyOrder(BUS_BRAND_ENTITY_1, BUS_BRAND_ENTITY_2));
    }

    @Test
    public void WHEN_queryByHashKey_THEN_shouldReturnMatchingBusBrand() {
        final Optional<BusBrandEntity> busBrandEntity = sqlRepository.getBusBrandByHashKey(BUS_BRAND_HASH_KEY_1);
        assertThat(busBrandEntity.get(), equalTo(BUS_BRAND_ENTITY_1));

        final Optional<BusBrandEntity> nonExistentBusBrandEntity = sqlRepository.getBusBrandByHashKey("invalid-key");
        assertThat(nonExistentBusBrandEntity.isPresent(), equalTo(false));
    }

    @Test
    public void WHEN_insertNewEntity_THEN_shouldReturnBusBrandId() {
        final String busBrandId = sqlRepository.putBusBrand(BusBrandEntity.builder()
                .name(ImmutableMap.of(
                        LANGUAGE_EN, "Leyland",
                        LANGUAGE_ZH, "利蘭"))
                .hashKey("leyland")
                .build());
        assertThat(busBrandId, notNullValue());

        final Optional<BusBrandEntity> busBrandEntity = sqlRepository.getBusBrandByHashKey("leyland");
        assertThat(busBrandEntity.isPresent(), equalTo(true));
    }

    @Override
    protected void setupDataForTest(Session session) {
        final Transaction transaction = session.beginTransaction();
        session.save(BUS_BRAND_ENTITY_1);
        session.save(BUS_BRAND_ENTITY_2);
        transaction.commit();
    }
}
