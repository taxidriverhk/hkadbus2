package com.taxidriverhk.hkadbus2.model.api;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GetBusModelsRequest {

    private String language;
}
