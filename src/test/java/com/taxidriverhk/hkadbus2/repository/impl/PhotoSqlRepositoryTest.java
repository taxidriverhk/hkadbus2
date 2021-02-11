package com.taxidriverhk.hkadbus2.repository.impl;

import com.taxidriverhk.hkadbus2.model.entity.PhotoEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.taxidriverhk.hkadbus2.util.MockDataHelper.ADVERTISEMENT_ENTITY_1;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.BUS_BRAND_ENTITY_1;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.BUS_ENTITY_1;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.BUS_MODEL_ENTITY_1;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.BUS_ROUTE_ENTITY_1;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.CATEGORY_ENTITY_1;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.PHOTO_ENTITY_1;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.PHOTO_SHORT_ID_1;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.USER_ENTITY_1;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PhotoSqlRepositoryTest extends SqlRepositoryTestBase {

    private PhotoSqlRepository sqlRepository;

    @BeforeEach
    public void setup() {
        sqlRepository = new PhotoSqlRepository(sessionFactory);
    }

    @Test
    public void WHEN_getPhotoByShortId_THEN_shouldReturnMatchingPhotoEntity() {
        final Optional<PhotoEntity> photoEntity = sqlRepository.getPhotoByShortId(PHOTO_SHORT_ID_1);
        assertThat(photoEntity.get(), equalTo(PHOTO_ENTITY_1));

        final Optional<PhotoEntity> nonExistentPhotoEntity = sqlRepository.getPhotoByShortId(5555L);
        assertThat(nonExistentPhotoEntity.isPresent(), equalTo(false));
    }

    @Override
    protected void setupDataForTest(Session session) {
        final Transaction transaction = session.beginTransaction();
        session.save(CATEGORY_ENTITY_1);
        session.save(ADVERTISEMENT_ENTITY_1);
        session.save(BUS_BRAND_ENTITY_1);
        session.save(BUS_MODEL_ENTITY_1);
        session.save(BUS_ENTITY_1);
        session.save(BUS_ROUTE_ENTITY_1);
        session.save(USER_ENTITY_1);
        session.save(PHOTO_ENTITY_1);
        transaction.commit();
    }
}
