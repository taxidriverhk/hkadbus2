package com.taxidriverhk.hkadbus2.service;

import com.taxidriverhk.hkadbus2.model.domain.Advertisement;

import java.util.List;

public interface AdvertisementService {

    List<Advertisement> getAdvertisements(String categoryId, String language);
}
