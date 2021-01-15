package com.taxidriverhk.hkadbus2.service.impl;

import com.taxidriverhk.hkadbus2.exception.ItemNotFoundException;
import com.taxidriverhk.hkadbus2.mapper.EntityMapper;
import com.taxidriverhk.hkadbus2.model.domain.Advertisement;
import com.taxidriverhk.hkadbus2.model.entity.AdvertisementEntity;
import com.taxidriverhk.hkadbus2.model.entity.CategoryEntity;
import com.taxidriverhk.hkadbus2.repository.AdvertisementRepository;
import com.taxidriverhk.hkadbus2.repository.CategoryRepository;
import com.taxidriverhk.hkadbus2.service.AdvertisementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@RequiredArgsConstructor(onConstructor = @__({@Inject}))
public class AdvertisementServiceImpl implements AdvertisementService {

    private final AdvertisementRepository advertisementRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public List<Advertisement> getAdvertisements(final String categoryId, final String language) {
        log.info("Fetching advertisements by category ID {}", categoryId);

        final CategoryEntity categoryEntity = categoryRepository.getCategoryByHashKey(categoryId)
                .orElseThrow(() -> new ItemNotFoundException(categoryId));
        final List<AdvertisementEntity> advertisementEntities = advertisementRepository.getAdvertisements(categoryEntity.getId().toString());
        return advertisementEntities.stream()
                .map(advertisementEntity -> EntityMapper.INSTANCE.advertisementEntityToAdvertisement(advertisementEntity, language))
                .collect(Collectors.toList());
    }
}
