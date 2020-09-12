package com.taxidriverhk.hkadbus2.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Builder(toBuilder = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusRouteEntity {

    public static final String HASH_KEY = "hashKey";
    public static final String ROUTE_NUMBER = "routeNumber";
    public static final String BUS_COMPANIES = "busCompanies";
    public static final String START_LOCATION = "startLocation";
    public static final String END_LOCATION = "endLocation";

    private String hashKey;
    private String routeNumber;
    private List<String> busCompanies;
    private Map<String, String> startLocation;
    private Map<String, String> endLocation;
}
