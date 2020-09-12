package com.taxidriverhk.hkadbus2.model.domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Photo {

    private String photoId;
    private String advertisementId;
    private String advertisement;
    private BusCompany busCompany;
    private String busModelId;
    private String busModel;
    private String busRouteId;
    private String busRoute;
    private String licensePlateNumber;
    private String fleetPrefix;
    private Integer fleetNumber;
    private String image;
    private String thumbnail;
    private String username;
    private Long uploadedDate;
}
