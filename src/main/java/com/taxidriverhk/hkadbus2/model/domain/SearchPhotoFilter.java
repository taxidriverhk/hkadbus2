package com.taxidriverhk.hkadbus2.model.domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SearchPhotoFilter {

    private String categoryHashKey;
    private String advertisementHashKey;
    private String busModelHashKey;
    private String busRouteNumber;
    private String fleetNumber;
    private String licensePlateNumber;
    private String uploaderName;
}
