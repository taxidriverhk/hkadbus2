package com.taxidriverhk.hkadbus2.service.impl;

import com.taxidriverhk.hkadbus2.mapper.EntityMapper;
import com.taxidriverhk.hkadbus2.model.domain.BusModel;
import com.taxidriverhk.hkadbus2.model.entity.BusBrandEntity;
import com.taxidriverhk.hkadbus2.model.entity.BusModelEntity;
import com.taxidriverhk.hkadbus2.repository.BusBrandRepository;
import com.taxidriverhk.hkadbus2.repository.BusModelRepository;
import com.taxidriverhk.hkadbus2.service.BusService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@RequiredArgsConstructor(onConstructor = @__({@Inject}))
public class BusServiceImpl implements BusService {

    private final BusBrandRepository busBrandRepository;
    private final BusModelRepository busModelRepository;

    @Override
    public List<BusModel> getBusModels(String language) {
        log.info("Fetching all bus brands from database.");
        final List<BusBrandEntity> busBrands = busBrandRepository.getBusBrands();

        log.info("Getting all bus models for {} bus brands.", busBrands.size());
        return busBrands.stream()
                .flatMap(busBrandEntity -> {
                    final List<BusModelEntity> busModelEntities = busModelRepository.getBusModelsByBrandId(busBrandEntity.getHashKey());
                    return busModelEntities.stream()
                            .map(busModelEntity -> EntityMapper.INSTANCE.busModelEntityToBusModel(busModelEntity, busBrandEntity, language));
                })
                .collect(Collectors.toList());
    }
}
