package com.taxidriverhk.hkadbus2.repository.impl;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.taxidriverhk.hkadbus2.model.entity.BusRouteEntity;
import com.taxidriverhk.hkadbus2.repository.BusRouteRepository;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.mongodb.client.model.Filters.eq;

public class BusRouteMongoDBRepository implements BusRouteRepository {

    private final MongoCollection<BusRouteEntity> mongoCollection;

    @Inject
    public BusRouteMongoDBRepository(final MongoDatabase mongoDatabase) {
        this.mongoCollection = mongoDatabase.getCollection("bus-routes", BusRouteEntity.class);
    }

    @Override
    public Optional<BusRouteEntity> getBusRouteByHashKey(String hashKey) {
        return Optional.ofNullable(mongoCollection.find(eq(BusRouteEntity.HASH_KEY, hashKey)).first());
    }

    @Override
    public List<BusRouteEntity> getBusRoutes(String routeNumber) {
        final FindIterable<BusRouteEntity> results = mongoCollection.find(eq(BusRouteEntity.ROUTE_NUMBER, routeNumber));
        return StreamSupport.stream(results.spliterator(), false)
                .collect(Collectors.toList());
    }
}
