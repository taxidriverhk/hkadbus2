package com.taxidriverhk.hkadbus2.repository.impl;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import de.bwaldvogel.mongo.MongoServer;
import de.bwaldvogel.mongo.backend.memory.MemoryBackend;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.junit.jupiter.api.BeforeEach;

import java.net.InetSocketAddress;

public abstract class MongoDBRepositoryTestBase<T> {

    protected MongoDatabase mongoDatabase;

    private final Class<T> entityClass;
    private final String collectionName;

    protected MongoDBRepositoryTestBase(final Class<T> entityClass, final String collectionName) {
        this.entityClass = entityClass;
        this.collectionName = collectionName;
    }

    @BeforeEach
    public void setupBase() {
        final CodecRegistry pojoCodecRegistry = CodecRegistries.fromProviders(PojoCodecProvider.builder()
                .automatic(true)
                .build());
        final CodecRegistry codecRegistry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);

        final MongoServer fakeMongoServer = new MongoServer(new MemoryBackend());
        final InetSocketAddress fakeMongoServerAddress = fakeMongoServer.bind();

        final MongoClientOptions mongoClientOptions = MongoClientOptions.builder()
                .codecRegistry(codecRegistry)
                .build();
        final MongoClient fakeMongoClient = new MongoClient(new ServerAddress(fakeMongoServerAddress), mongoClientOptions);

        mongoDatabase = fakeMongoClient.getDatabase("hkadbus2");
        setupDataForTests(mongoDatabase.getCollection(collectionName, entityClass));
    }

    protected abstract void setupDataForTests(final MongoCollection<T> mongoCollection);
}
