package com.taxidriverhk.hkadbus2.module;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.taxidriverhk.hkadbus2.repository.AdvertisementRepository;
import com.taxidriverhk.hkadbus2.repository.CategoryRepository;
import com.taxidriverhk.hkadbus2.repository.impl.AdvertisementMongoDBRepository;
import com.taxidriverhk.hkadbus2.repository.impl.CategoryMongoDBRepository;
import dagger.Module;
import dagger.Provides;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import javax.inject.Singleton;
import java.util.Properties;

@Module
public class DataAccessModule {

    @Provides
    public MongoDatabase mongoDatabase(final Properties applicationProperties) {
        final ConnectionString connectionString = new ConnectionString(
                applicationProperties.getProperty("datasource.mongodb.connectionString"));

        final CodecRegistry pojoCodecRegistry = CodecRegistries.fromProviders(PojoCodecProvider.builder()
                .automatic(true)
                .build());
        final CodecRegistry codecRegistry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);

        final MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .codecRegistry(codecRegistry)
                .build();
        final MongoClient mongoClient = MongoClients.create(mongoClientSettings);
        return mongoClient.getDatabase("hkadbus2");
    }

    @Provides
    public AdvertisementRepository advertisementRepository(final MongoDatabase mongoDatabase) {
        return new AdvertisementMongoDBRepository(mongoDatabase);
    }

    @Provides
    public CategoryRepository categoryRepository(final MongoDatabase mongoDatabase) {
        return new CategoryMongoDBRepository(mongoDatabase);
    }
}
