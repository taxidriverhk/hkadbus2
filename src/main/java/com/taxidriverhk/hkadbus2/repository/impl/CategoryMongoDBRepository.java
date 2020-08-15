package com.taxidriverhk.hkadbus2.repository.impl;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.taxidriverhk.hkadbus2.model.entity.CategoryEntity;
import com.taxidriverhk.hkadbus2.repository.CategoryRepository;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.mongodb.client.model.Filters.eq;

public class CategoryMongoDBRepository implements CategoryRepository {

    private final MongoCollection<CategoryEntity> mongoCollection;

    @Inject
    public CategoryMongoDBRepository(final MongoDatabase mongoDatabase) {
        this.mongoCollection = mongoDatabase.getCollection("categories", CategoryEntity.class);
    }

    @Override
    public List<CategoryEntity> getCategories() {
        return StreamSupport.stream(mongoCollection.find().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CategoryEntity> getCategoryByHashKey(String hashKey) {
        return Optional.ofNullable(mongoCollection.find(eq("hashKey", hashKey)).first());
    }
}
