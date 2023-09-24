package com.taxidriverhk.hkadbus2.repository.impl;

import static com.taxidriverhk.hkadbus2.util.MockDataHelper.USER_ENTITY_1;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.USER_ENTITY_2;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.taxidriverhk.hkadbus2.model.entity.UserEntity;

public class UserSqlRepositoryTest extends SqlRepositoryTestBase {

    private UserSqlRepository sqlRepository;

    @BeforeEach
    public void setup() {
        sqlRepository = new UserSqlRepository(sessionFactory);
    }

    @Test
    public void WHEN_getUsers_THEN_shouldReturnAllUserEntities() {
        final List<UserEntity> userEntities = sqlRepository.getUsers();
        assertThat(userEntities, hasSize(2));
    }

    @Test
    public void WHEN_getUserByUsername_THEN_shouldReturnMatchingUserEntity() {
        final Optional<UserEntity> userEntity = sqlRepository.getUserByUsername("test-user");
        assertThat(userEntity.get(), equalTo(USER_ENTITY_1));
    }

    @Override
    protected void setupDataForTest(Session session) {
        final Transaction transaction = session.beginTransaction();
        session.save(USER_ENTITY_1);
        session.save(USER_ENTITY_2);
        transaction.commit();
    }
}
