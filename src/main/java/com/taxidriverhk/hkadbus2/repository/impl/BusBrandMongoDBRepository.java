package com.taxidriverhk.hkadbus2.repository.impl;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.taxidriverhk.hkadbus2.model.entity.BusBrandEntity;
import com.taxidriverhk.hkadbus2.repository.BusBrandRepository;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.mongodb.client.model.Filters.eq;

public class BusBrandMongoDBRepository implements BusBrandRepository {

    private final MongoCollection<BusBrandEntity> mongoCollection;

    @Inject
    public BusBrandMongoDBRepository(final MongoDatabase mongoDatabase) {
        this.mongoCollection = mongoDatabase.getCollection("bus-brands", BusBrandEntity.class);
    }

    @Override
    public List<BusBrandEntity> getBusBrands() {
        return StreamSupport.stream(mongoCollection.find().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<BusBrandEntity> getBusBrandByHashKey(String hashKey) {
        return Optional.ofNullable(mongoCollection.find(eq(BusBrandEntity.HASH_KEY, hashKey)).first());
    }
}
