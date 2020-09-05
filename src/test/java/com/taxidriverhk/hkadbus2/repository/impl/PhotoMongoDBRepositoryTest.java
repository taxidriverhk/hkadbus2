package com.taxidriverhk.hkadbus2.repository.impl;

import com.mongodb.client.MongoCollection;
import com.taxidriverhk.hkadbus2.model.entity.PhotoEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.taxidriverhk.hkadbus2.util.MockDataHelper.PHOTO_ENTITY_1;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.PHOTO_ID_1;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PhotoMongoDBRepositoryTest extends MongoDBRepositoryTestBase<PhotoEntity> {

    private PhotoMongoDBRepository photoMongoDBRepository;

    public PhotoMongoDBRepositoryTest() {
        super(PhotoEntity.class, "photos");
    }

    @BeforeEach
    public void setup() {
        photoMongoDBRepository = new PhotoMongoDBRepository(mongoDatabase);
    }

    @Test
    public void WHEN_queryPhotoByValidId_THEN_shouldReturnMatchingEntity() {
        assertThat(photoMongoDBRepository.getPhoto(PHOTO_ID_1), equalTo(Optional.of(PHOTO_ENTITY_1)));
    }

    @Test
    public void WHEN_queryPhotoByNonExistingId_THEN_shouldReturnNothing() {
        assertThat(photoMongoDBRepository.getPhoto("invalid"), equalTo(Optional.empty()));
    }

    @Override
    protected void setupDataForTests(final MongoCollection<PhotoEntity> mongoCollection) {
        mongoCollection.insertOne(PHOTO_ENTITY_1);
    }
}
