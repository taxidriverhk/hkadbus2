package com.taxidriverhk.hkadbus2.model.domain;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SearchPhotoFilter {

    private List<String> categoryNames;
    private List<String> categoryIds;
    private List<String> advertisementNames;
    private List<String> advertisementIds;
    private List<String> busCompanyNames;
    private List<String> busModelNames;
    private List<String> busModelIds;
    private List<String> busRouteNumbers;
    private List<String> busRouteIds;
    private List<String> fleetPrefixes;
    private List<String> licensePlateNumbers;
    private List<String> uploaderNames;
    private List<String> thumbnails; 
    private String language;
}
