package com.taxidriverhk.hkadbus2.repository;

import java.util.List;
import java.util.Optional;

import com.taxidriverhk.hkadbus2.model.entity.AdvertisementEntity;

public interface AdvertisementRepository {

    Optional<AdvertisementEntity> getAdvertisementByHashKey(String hashKey);
    List<AdvertisementEntity> getAdvertisements(String categoryId);
    String putAdvertisement(AdvertisementEntity advertisement);
}
