package com.taxidriverhk.hkadbus2.repository.impl;

import com.mongodb.client.MongoCollection;
import com.taxidriverhk.hkadbus2.model.entity.AdvertisementEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.taxidriverhk.hkadbus2.util.MockDataHelper.ADVERTISEMENT_ENTITY_1;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.ADVERTISEMENT_ENTITY_2;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.ADVERTISEMENT_ENTITY_3;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.CATEGORY_HASH_KEY_1;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

public class AdvertisementMongoDBRepositoryTest extends MongoDBRepositoryTestBase<AdvertisementEntity> {

    private AdvertisementMongoDBRepository advertisementMongoDBRepository;

    public AdvertisementMongoDBRepositoryTest() {
        super(AdvertisementEntity.class, "advertisements");
    }

    @BeforeEach
    public void setup() {
        advertisementMongoDBRepository = new AdvertisementMongoDBRepository(mongoDatabase);
    }

    @Test
    public void WHEN_getAdvertisementsByCategoryId_THEN_shouldFindAllMatchingAdvertisementEntities() {
        final List<AdvertisementEntity> advertisementEntities = advertisementMongoDBRepository.getAdvertisements(CATEGORY_HASH_KEY_1);
        assertThat(advertisementEntities, containsInAnyOrder(
                ADVERTISEMENT_ENTITY_1,
                ADVERTISEMENT_ENTITY_2));
    }

    @Override
    protected void setupDataForTests(final MongoCollection<AdvertisementEntity> mongoCollection) {
        mongoCollection.insertOne(ADVERTISEMENT_ENTITY_1);
        mongoCollection.insertOne(ADVERTISEMENT_ENTITY_2);
        mongoCollection.insertOne(ADVERTISEMENT_ENTITY_3);
    }
}
