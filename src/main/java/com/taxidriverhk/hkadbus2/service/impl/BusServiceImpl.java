package com.taxidriverhk.hkadbus2.service.impl;

import com.google.common.collect.Lists;
import com.taxidriverhk.hkadbus2.model.domain.BusModel;
import com.taxidriverhk.hkadbus2.service.BusService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import javax.inject.Inject;
import java.util.List;

@Log4j2
@RequiredArgsConstructor(onConstructor = @__({@Inject}))
public class BusServiceImpl implements BusService {

    @Override
    public List<BusModel> getBusModels(String language) {
        return Lists.newArrayList();
    }
}
