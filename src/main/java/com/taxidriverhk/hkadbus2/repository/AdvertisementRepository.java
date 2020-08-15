package com.taxidriverhk.hkadbus2.repository;

import com.taxidriverhk.hkadbus2.model.entity.AdvertisementEntity;

import java.util.List;

public interface AdvertisementRepository {

    List<AdvertisementEntity> getAdvertisements(String categoryId);
}
