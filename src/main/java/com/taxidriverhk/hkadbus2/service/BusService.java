package com.taxidriverhk.hkadbus2.service;

import com.taxidriverhk.hkadbus2.model.domain.BusModel;

import java.util.List;

public interface BusService {

    List<BusModel> getBusModels(String language);
}
