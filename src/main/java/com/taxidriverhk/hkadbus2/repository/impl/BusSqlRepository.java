package com.taxidriverhk.hkadbus2.repository.impl;

import java.util.List;
import java.util.Optional;

import org.hibernate.SessionFactory;

import com.taxidriverhk.hkadbus2.model.entity.BusEntity;
import com.taxidriverhk.hkadbus2.repository.BusRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Log4j2
public class BusSqlRepository implements BusRepository {

    private final SessionFactory sessionFactory;

    @Override
    public Optional<BusEntity> getBus(String busId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBus'");
    }

    @Override
    public List<BusEntity> getBuses(String busModelId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBuses'");
    }

    @Override
    public String putBus(BusEntity bus) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'putBus'");
    }
    
}
