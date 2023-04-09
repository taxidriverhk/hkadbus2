package com.taxidriverhk.hkadbus2.repository.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.taxidriverhk.hkadbus2.model.entity.BusEntity;
import com.taxidriverhk.hkadbus2.model.entity.EntityConstants;
import com.taxidriverhk.hkadbus2.repository.BusRepository;
import com.taxidriverhk.hkadbus2.util.SqlQueryUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Log4j2
public class BusSqlRepository implements BusRepository {

    private final SessionFactory sessionFactory;

    @Override
    public Optional<BusEntity> getBus(String busId) {
        log.info("Querying bus by ID {}", busId);

        final Session session = sessionFactory.openSession();
        final Optional<BusEntity> result = SqlQueryUtil.selectSingleItemByPrimaryKey(session, BusEntity.class, busId);
        session.close();

        return result;
    }

    @Override
    public List<BusEntity> getBuses(String busModelId) {
        log.info("Querying buses by model ID {}", busModelId);
        
        final Session session = sessionFactory.openSession();
        final List<BusEntity> results = SqlQueryUtil.selectMatchingItemsByForeignKey(
                session,
                BusEntity.class,
                "busModel",
                EntityConstants.ID_ATTRIBUTE,
                UUID.fromString(busModelId));
        session.close();
        return results;
    }

    @Override
    public String putBus(BusEntity bus) {
        log.info("Inserting bus with entity {}", bus);

        final Session session = sessionFactory.openSession();
        final BusEntity result = SqlQueryUtil.mutateWithTransaction(
                session, 
                BusEntity.class,
                () -> SqlQueryUtil.insertEntity(
                        session,
                        BusEntity.class,
                        bus
                ));
        session.close();
        return result.getId().toString();
    }
}
