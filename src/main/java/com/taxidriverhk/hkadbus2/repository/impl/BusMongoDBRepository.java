package com.taxidriverhk.hkadbus2.repository.impl;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.taxidriverhk.hkadbus2.model.entity.BusEntity;
import com.taxidriverhk.hkadbus2.repository.BusRepository;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.mongodb.client.model.Filters.eq;

public class BusMongoDBRepository implements BusRepository {

    private final MongoCollection<BusEntity> mongoCollection;

    @Inject
    public BusMongoDBRepository(final MongoDatabase mongoDatabase) {
        this.mongoCollection = mongoDatabase.getCollection("buses", BusEntity.class);
    }

    @Override
    public Optional<BusEntity> getBus(String busId) {
        return Optional.ofNullable(mongoCollection.find(eq(BusEntity.BUS_ID, busId)).first());
    }

    @Override
    public List<BusEntity> getBuses(String busModelId) {
        final FindIterable<BusEntity> results = mongoCollection.find(eq(BusEntity.BUS_MODEL_ID, busModelId));
        return StreamSupport.stream(results.spliterator(), false)
                .collect(Collectors.toList());
    }
}
