package com.taxidriverhk.hkadbus2.repository.impl;

import com.taxidriverhk.hkadbus2.model.entity.BusBrandEntity;
import com.taxidriverhk.hkadbus2.model.entity.EntityConstants;
import com.taxidriverhk.hkadbus2.repository.BusBrandRepository;
import com.taxidriverhk.hkadbus2.util.SqlQueryUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Log4j2
public class BusBrandSqlRepository implements BusBrandRepository {

    private final SessionFactory sessionFactory;

    @Override
    public List<BusBrandEntity> getBusBrands() {
        log.info("Querying all bus brands");

        final Session session = sessionFactory.openSession();
        final List<BusBrandEntity> results = SqlQueryUtil.selectAll(session, BusBrandEntity.class);
        session.close();
        return results;
    }

    @Override
    public Optional<BusBrandEntity> getBusBrandByHashKey(final String hashKey) {
        log.info("Querying bus brands by hash key {}", hashKey);

        final Session session = sessionFactory.openSession();
        final Optional<BusBrandEntity> result = SqlQueryUtil.selectSingleItemByCompositeKey(
                session,
                BusBrandEntity.class,
                EntityConstants.HASH_KEY_ATTRIBUTE,
                hashKey);
        session.close();
        return result;
    }
}
