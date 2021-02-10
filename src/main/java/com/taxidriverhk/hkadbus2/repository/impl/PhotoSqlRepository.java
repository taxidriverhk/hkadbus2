package com.taxidriverhk.hkadbus2.repository.impl;

import com.taxidriverhk.hkadbus2.model.entity.EntityConstants;
import com.taxidriverhk.hkadbus2.model.entity.PhotoEntity;
import com.taxidriverhk.hkadbus2.repository.PhotoRepository;
import com.taxidriverhk.hkadbus2.util.SqlQueryUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Optional;

@RequiredArgsConstructor
@Log4j2
public class PhotoSqlRepository implements PhotoRepository {

    private final SessionFactory sessionFactory;

    @Override
    public Optional<PhotoEntity> getPhotoByShortId(final Long shortId) {
        log.info("Querying photo by short ID {}", shortId);

        final Session session = sessionFactory.openSession();
        final Optional<PhotoEntity> result = SqlQueryUtil.selectSingleItemByCompositeKey(
                session,
                PhotoEntity.class,
                EntityConstants.SHORT_ID_ATTRIBUTE,
                shortId);
        session.close();
        return result;
    }
}
