package com.taxidriverhk.hkadbus2.repository.impl;

import com.taxidriverhk.hkadbus2.model.entity.CategoryEntity;
import com.taxidriverhk.hkadbus2.repository.CategoryRepository;
import com.taxidriverhk.hkadbus2.util.SqlQueryUtil;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.inject.Inject;
import java.util.List;

@RequiredArgsConstructor(onConstructor = @__({@Inject}))
public class CategorySqlRepository implements CategoryRepository {

    private final SessionFactory sessionFactory;

    @Override
    public List<CategoryEntity> getCategories() {
        final Session session = sessionFactory.openSession();
        final List<CategoryEntity> results = SqlQueryUtil.selectAll(session, CategoryEntity.class);
        session.close();
        return results;
    }
}
