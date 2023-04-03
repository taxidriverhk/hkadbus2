package com.taxidriverhk.hkadbus2.repository.impl;

import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.taxidriverhk.hkadbus2.model.entity.UserEntity;
import com.taxidriverhk.hkadbus2.repository.UserRepository;
import com.taxidriverhk.hkadbus2.util.SqlQueryUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Log4j2
public class UserSqlRepository implements UserRepository {

    private final SessionFactory sessionFactory;

    @Override
    public Optional<UserEntity> getUserByUsername(final String username) {
        log.info("Querying user by username {}", username);

        final Session session = sessionFactory.openSession();
        final Optional<UserEntity> result = SqlQueryUtil.selectSingleItemByCompositeKey(
                session,
                UserEntity.class,
                "username",
                username);
        session.close();
        return result;
    }
    
}
