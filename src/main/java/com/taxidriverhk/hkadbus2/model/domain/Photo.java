package com.taxidriverhk.hkadbus2.model.domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Photo {

    private String photoId;
    private String advertisement;
    private String busCompany;
    private String busModel;
    private String busRoute;
    private String licensePlateNumber;
    private String fleetPrefix;
    private Integer fleetNumber;
    private String thumbnail;
    private String username;
    private Long uploadedDate;
}
