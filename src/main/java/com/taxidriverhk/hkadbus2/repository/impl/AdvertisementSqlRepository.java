package com.taxidriverhk.hkadbus2.repository.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.taxidriverhk.hkadbus2.model.entity.AdvertisementEntity;
import com.taxidriverhk.hkadbus2.model.entity.EntityConstants;
import com.taxidriverhk.hkadbus2.repository.AdvertisementRepository;
import com.taxidriverhk.hkadbus2.util.SqlQueryUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Log4j2
public class AdvertisementSqlRepository implements AdvertisementRepository {

    private final SessionFactory sessionFactory;

    @Override
    public Optional<AdvertisementEntity> getAdvertisementByHashKey(final String hashKey) {
        log.info("Querying advertisement by hash key {}", hashKey);

        final Session session = sessionFactory.openSession();
        final Optional<AdvertisementEntity> result = SqlQueryUtil.selectSingleItemByCompositeKey(
                session,
                AdvertisementEntity.class,
                EntityConstants.HASH_KEY_ATTRIBUTE,
                hashKey);
        session.close();
        return result;
    }

    @Override
    public List<AdvertisementEntity> getAdvertisements(final String categoryId) {
        log.info("Querying advertisements by category ID {}", categoryId);

        final Session session = sessionFactory.openSession();
        final List<AdvertisementEntity> results = SqlQueryUtil.selectMatchingItemsByForeignKey(
                session,
                AdvertisementEntity.class,
                EntityConstants.CATEGORY_TABLE,
                EntityConstants.ID_ATTRIBUTE,
                UUID.fromString(categoryId));
        session.close();
        return results;
    }

    @Override
    public String putAdvertisement(final AdvertisementEntity advertisement) {
        log.info("Inserting advertisement with entity {}", advertisement);

        final Session session = sessionFactory.openSession();
        final AdvertisementEntity result = SqlQueryUtil.mutateWithTransaction(
                session, 
                AdvertisementEntity.class,
                () -> SqlQueryUtil.insertEntity(
                        session,
                        AdvertisementEntity.class,
                        advertisement
                ));
        session.close();
        return result.getId().toString();
    }
}
