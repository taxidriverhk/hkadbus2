package com.taxidriverhk.hkadbus2.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import com.taxidriverhk.hkadbus2.mapper.EntityMapper;
import com.taxidriverhk.hkadbus2.model.domain.BusModel;
import com.taxidriverhk.hkadbus2.model.entity.BusBrandEntity;
import com.taxidriverhk.hkadbus2.model.entity.BusModelEntity;
import com.taxidriverhk.hkadbus2.repository.BusBrandRepository;
import com.taxidriverhk.hkadbus2.repository.BusModelRepository;
import com.taxidriverhk.hkadbus2.service.BusService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(onConstructor = @__({@Inject}))
public class BusServiceImpl implements BusService {

    private final BusBrandRepository busBrandRepository;
    private final BusModelRepository busModelRepository;

    @Override
    public List<BusModel> getBusModels(final String language) {
        final List<BusBrandEntity> busBrandEntities = busBrandRepository.getBusBrands();
        final List<BusModelEntity> busModelEntities = busBrandEntities.stream()
                .map(busBrandEntity -> busModelRepository.getBusModels(busBrandEntity.getId().toString()))
                .flatMap(List::stream)
                .collect(Collectors.toList());
        return busModelEntities.stream()
                .map(busModelEntity -> EntityMapper.INSTANCE.busModelEntityToBusModel(busModelEntity, language))
                .sorted((m1, m2) -> m1.getName().compareTo(m2.getName()))
                .collect(Collectors.toList());
    }
}
