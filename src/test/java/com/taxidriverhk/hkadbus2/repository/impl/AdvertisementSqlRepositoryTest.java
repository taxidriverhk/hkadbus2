package com.taxidriverhk.hkadbus2.repository.impl;

import com.taxidriverhk.hkadbus2.model.entity.AdvertisementEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static com.taxidriverhk.hkadbus2.util.MockDataHelper.ADVERTISEMENT_ENTITY_1;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.ADVERTISEMENT_ENTITY_2;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.ADVERTISEMENT_ENTITY_3;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.ADVERTISEMENT_HASH_KEY_1;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.CATEGORY_ENTITY_1;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.CATEGORY_ENTITY_2;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.CATEGORY_ENTITY_ID_1;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;

public class AdvertisementSqlRepositoryTest extends SqlRepositoryTestBase {

    private AdvertisementSqlRepository sqlRepository;

    @BeforeEach
    public void setup() {
        sqlRepository = new AdvertisementSqlRepository(sessionFactory);
    }

    @Test
    public void WHEN_queryByCategoryId_THEN_shouldReturnMatchingAdvertisements() {
        final List<AdvertisementEntity> advertisementEntities = sqlRepository.getAdvertisements(CATEGORY_ENTITY_1.getId().toString());
        assertThat(advertisementEntities, containsInAnyOrder(ADVERTISEMENT_ENTITY_1, ADVERTISEMENT_ENTITY_2));
    }

    @Test
    public void WHEN_queryByHashKey_THEN_shouldReturnMatchingAdvertisement() {
        final Optional<AdvertisementEntity> advertisementEntity = sqlRepository.getAdvertisementByHashKey(ADVERTISEMENT_HASH_KEY_1);
        assertThat(advertisementEntity.get(), equalTo(ADVERTISEMENT_ENTITY_1));

        final Optional<AdvertisementEntity> nonExistentAdvertisementEntity = sqlRepository.getAdvertisementByHashKey("invalid-key");
        assertThat(nonExistentAdvertisementEntity.isPresent(), equalTo(false));
    }

    @Override
    protected void setupDataForTest(Session session) {
        final Transaction transaction = session.beginTransaction();
        session.save(CATEGORY_ENTITY_1);
        session.save(CATEGORY_ENTITY_2);
        session.save(ADVERTISEMENT_ENTITY_1);
        session.save(ADVERTISEMENT_ENTITY_2);
        session.save(ADVERTISEMENT_ENTITY_3);
        transaction.commit();
    }
}
