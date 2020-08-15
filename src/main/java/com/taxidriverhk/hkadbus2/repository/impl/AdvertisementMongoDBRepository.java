package com.taxidriverhk.hkadbus2.repository.impl;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.taxidriverhk.hkadbus2.model.entity.AdvertisementEntity;
import com.taxidriverhk.hkadbus2.repository.AdvertisementRepository;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.mongodb.client.model.Filters.eq;

public class AdvertisementMongoDBRepository implements AdvertisementRepository {

    private final MongoCollection<AdvertisementEntity> mongoCollection;

    @Inject
    public AdvertisementMongoDBRepository(final MongoDatabase mongoDatabase) {
        this.mongoCollection = mongoDatabase.getCollection("advertisements", AdvertisementEntity.class);
    }

    @Override
    public List<AdvertisementEntity> getAdvertisements(String categoryId) {
        final FindIterable<AdvertisementEntity> results = mongoCollection.find(eq("categoryId", categoryId));
        return StreamSupport.stream(results.spliterator(), false)
                .collect(Collectors.toList());
    }
}
