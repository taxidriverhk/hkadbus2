package com.taxidriverhk.hkadbus2.repository.impl;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.taxidriverhk.hkadbus2.model.entity.PhotoEntity;
import com.taxidriverhk.hkadbus2.repository.PhotoRepository;

import javax.inject.Inject;
import java.util.Optional;

import static com.mongodb.client.model.Filters.eq;

public class PhotoMongoDBRepository implements PhotoRepository {

    private final MongoCollection<PhotoEntity> mongoCollection;

    @Inject
    public PhotoMongoDBRepository(final MongoDatabase mongoDatabase) {
        this.mongoCollection = mongoDatabase.getCollection("photos", PhotoEntity.class);
    }

    @Override
    public Optional<PhotoEntity> getPhoto(String id) {
        return Optional.ofNullable(mongoCollection.find(eq("photoId", id)).first());
    }
}
