package com.taxidriverhk.hkadbus2.repository.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.taxidriverhk.hkadbus2.model.entity.BusModelEntity;
import com.taxidriverhk.hkadbus2.model.entity.EntityConstants;
import com.taxidriverhk.hkadbus2.repository.BusModelRepository;
import com.taxidriverhk.hkadbus2.util.SqlQueryUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Log4j2
public class BusModelSqlRepository implements BusModelRepository {

    private final SessionFactory sessionFactory;

    @Override
    public List<BusModelEntity> getBusModels(final String brandId) {
        log.info("Querying bus models by brand ID {}", brandId);

        final Session session = sessionFactory.openSession();
        final List<BusModelEntity> results = SqlQueryUtil.selectMatchingItemsByForeignKey(
                session,
                BusModelEntity.class,
                "busBrand",
                EntityConstants.ID_ATTRIBUTE,
                UUID.fromString(brandId));
        session.close();
        return results;
    }

    @Override
    public Optional<BusModelEntity> getBusModelByHashKey(final String hashKey) {
        log.info("Querying bus model by hash key {}", hashKey);

        final Session session = sessionFactory.openSession();
        final Optional<BusModelEntity> result = SqlQueryUtil.selectSingleItemByCompositeKey(
                session,
                BusModelEntity.class,
                EntityConstants.HASH_KEY_ATTRIBUTE,
                hashKey);
        session.close();
        return result;
    }

    @Override
    public String putBusModel(final BusModelEntity busModel) {
        log.info("Inserting bus model with entity {}", busModel);

        final Session session = sessionFactory.openSession();
        final BusModelEntity result = SqlQueryUtil.mutateWithTransaction(
                session, 
                BusModelEntity.class,
                () -> SqlQueryUtil.insertEntity(
                        session,
                        BusModelEntity.class,
                        busModel
                ));
        session.close();
        return result.getId().toString();
    }
}
