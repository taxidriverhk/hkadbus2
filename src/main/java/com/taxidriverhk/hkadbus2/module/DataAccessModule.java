package com.taxidriverhk.hkadbus2.module;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.taxidriverhk.hkadbus2.repository.AdvertisementRepository;
import com.taxidriverhk.hkadbus2.repository.BusBrandRepository;
import com.taxidriverhk.hkadbus2.repository.BusModelRepository;
import com.taxidriverhk.hkadbus2.repository.CategoryRepository;
import com.taxidriverhk.hkadbus2.repository.PhotoRepository;
import com.taxidriverhk.hkadbus2.repository.impl.AdvertisementMongoDBRepository;
import com.taxidriverhk.hkadbus2.repository.impl.BusBrandMongoDBRepository;
import com.taxidriverhk.hkadbus2.repository.impl.BusModelMongoDBRepository;
import com.taxidriverhk.hkadbus2.repository.impl.CategoryMongoDBRepository;
import com.taxidriverhk.hkadbus2.repository.impl.PhotoMongoDBRepository;
import dagger.Module;
import dagger.Provides;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

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
    public BusBrandRepository busBrandRepository(final MongoDatabase mongoDatabase) {
        return new BusBrandMongoDBRepository(mongoDatabase);
    }

    @Provides
    public BusModelRepository busModelRepository(final MongoDatabase mongoDatabase) {
        return new BusModelMongoDBRepository(mongoDatabase);
    }

    @Provides
    public CategoryRepository categoryRepository(final MongoDatabase mongoDatabase) {
        return new CategoryMongoDBRepository(mongoDatabase);
    }

    @Provides
    public PhotoRepository photoRepository(final MongoDatabase mongoDatabase) {
        return new PhotoMongoDBRepository(mongoDatabase);
    }
}
