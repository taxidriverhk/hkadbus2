package com.taxidriverhk.hkadbus2.repository;

import com.taxidriverhk.hkadbus2.model.entity.AdvertisementEntity;

import java.util.List;
import java.util.Optional;

public interface AdvertisementRepository {

    Optional<AdvertisementEntity> getAdvertisementByHashKey(String hashKey);
    List<AdvertisementEntity> getAdvertisements(String categoryId);
}
