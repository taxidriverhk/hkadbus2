package com.taxidriverhk.hkadbus2.model.api;

import com.taxidriverhk.hkadbus2.model.domain.BusModel;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class GetBusModelsResponse {

    private List<BusModel> busModels;
}
