package com.taxidriverhk.hkadbus2.repository.impl;

import com.taxidriverhk.hkadbus2.model.entity.CategoryEntity;
import com.taxidriverhk.hkadbus2.model.entity.EntityConstants;
import com.taxidriverhk.hkadbus2.repository.CategoryRepository;
import com.taxidriverhk.hkadbus2.util.SqlQueryUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Log4j2
public class CategorySqlRepository implements CategoryRepository {

    private final SessionFactory sessionFactory;

    @Override
    public List<CategoryEntity> getCategories() {
        log.info("Querying all categories");

        final Session session = sessionFactory.openSession();
        final List<CategoryEntity> results = SqlQueryUtil.selectAll(session, CategoryEntity.class);
        session.close();
        return results;
    }

    @Override
    public Optional<CategoryEntity> getCategoryByHashKey(final String hashKey) {
        log.info("Querying category by hash key {}", hashKey);

        final Session session = sessionFactory.openSession();
        final Optional<CategoryEntity> result = SqlQueryUtil.selectSingleItemByCompositeKey(
                session,
                CategoryEntity.class,
                EntityConstants.HASH_KEY_ATTRIBUTE,
                hashKey);
        session.close();
        return result;
    }
}
