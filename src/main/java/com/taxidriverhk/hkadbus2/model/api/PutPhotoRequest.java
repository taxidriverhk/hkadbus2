package com.taxidriverhk.hkadbus2.model.api;

import java.util.Map;

import com.taxidriverhk.hkadbus2.model.domain.BusCompany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PutPhotoRequest {

    private String advertisementId;
    private Map<String, String> advertisementNames;
    private String categoryId;
    private Map<String, String> categoryNames;
    private String busBrandId;
    private Map<String, String> busBrandNames;
    private String busModelId;
    private Map<String, String> busModelNames;
    private BusCompany busCompany;
    private String routeNumber;
    private String busRouteId;
    private Map<String, String> busRouteStartLocationNames;
    private Map<String, String> busRouteEndLocationNames;
    private String licensePlateNumber;
    private String fleetPrefix;
    private String fleetNumber;
    private String image;
    private String thumbnail;
    private String username;
}
