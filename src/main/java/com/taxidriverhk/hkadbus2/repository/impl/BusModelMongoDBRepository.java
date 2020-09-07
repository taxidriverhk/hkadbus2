package com.taxidriverhk.hkadbus2.repository.impl;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.taxidriverhk.hkadbus2.model.entity.BusModelEntity;
import com.taxidriverhk.hkadbus2.repository.BusModelRepository;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.mongodb.client.model.Filters.eq;

public class BusModelMongoDBRepository implements BusModelRepository {

    private final MongoCollection<BusModelEntity> mongoCollection;

    @Inject
    public BusModelMongoDBRepository(final MongoDatabase mongoDatabase) {
        this.mongoCollection = mongoDatabase.getCollection("bus-models", BusModelEntity.class);
    }

    @Override
    public List<BusModelEntity> getBusModelsByBrandId(String brandId) {
        return StreamSupport.stream(mongoCollection.find(eq(BusModelEntity.BUS_BRAND_ID, brandId)).spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<BusModelEntity> getBusModelByHashKey(String hashKey) {
        return Optional.ofNullable(mongoCollection.find(eq(BusModelEntity.HASH_KEY, hashKey)).first());
    }
}
