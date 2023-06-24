package com.taxidriverhk.hkadbus2.model.api;

import java.util.Map;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GetEntityOptionsResponse {

    private String entityType;
    // Hash key -> label
    private Map<String, String> options;
}
