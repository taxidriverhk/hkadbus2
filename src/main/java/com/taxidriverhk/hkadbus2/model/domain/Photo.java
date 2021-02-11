package com.taxidriverhk.hkadbus2.model.domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Photo {

    private Long photoId;
    private String advertisementId;
    private String advertisement;
    private String categoryId;
    private String category;
    private BusCompany busCompany;
    private Long busId;
    private String busModelId;
    private String busModel;
    private String routeNumber;
    private String licensePlateNumber;
    private String fleetPrefix;
    private String fleetNumber;
    private String image;
    private String thumbnail;
    private String username;
    private Long uploadedDate;
}
