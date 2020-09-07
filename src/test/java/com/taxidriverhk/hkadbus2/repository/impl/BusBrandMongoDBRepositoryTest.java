package com.taxidriverhk.hkadbus2.repository.impl;

import com.mongodb.client.MongoCollection;
import com.taxidriverhk.hkadbus2.model.entity.BusBrandEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static com.taxidriverhk.hkadbus2.util.MockDataHelper.BUS_BRAND_ENTITY_1;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.BUS_BRAND_HASH_KEY_1;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;

public class BusBrandMongoDBRepositoryTest extends MongoDBRepositoryTestBase<BusBrandEntity> {

    private BusBrandMongoDBRepository busBrandMongoDBRepository;

    public BusBrandMongoDBRepositoryTest() {
        super(BusBrandEntity.class, "bus-brands");
    }

    @BeforeEach
    public void setup() {
        busBrandMongoDBRepository = new BusBrandMongoDBRepository(mongoDatabase);
    }

    @Test
    public void WHEN_getBusBrands_THEN_shouldFindAllBusBrandsFromCollection() {
        final List<BusBrandEntity> busBrandEntities = busBrandMongoDBRepository.getBusBrands();
        assertThat(busBrandEntities, containsInAnyOrder(BUS_BRAND_ENTITY_1));
    }

    @Test
    public void WHEN_queryBusBrandByValidHashKey_THEN_shouldReturnMatchingEntity() {
        assertThat(busBrandMongoDBRepository.getBusBrandByHashKey(BUS_BRAND_HASH_KEY_1), equalTo(Optional.of(BUS_BRAND_ENTITY_1)));
    }

    @Test
    public void WHEN_queryBusBrandByInvalidHashKey_THEN_shouldReturnNothing() {
        assertThat(busBrandMongoDBRepository.getBusBrandByHashKey("invalid"), equalTo(Optional.empty()));
    }

    @Override
    protected void setupDataForTests(MongoCollection<BusBrandEntity> mongoCollection) {
        mongoCollection.insertOne(BUS_BRAND_ENTITY_1);
    }
}
