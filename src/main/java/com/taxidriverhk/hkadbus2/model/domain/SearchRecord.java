package com.taxidriverhk.hkadbus2.model.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class SearchRecord {

    private Long photoId;
    private String advertisementId;
    private String advertisement;
    private String categoryId;
    private String category;
    private BusCompany busCompany;
    private String busModelId;
    private String busModel;
    private String routeId;
    private String routeNumber;
    private String licensePlateNumber;
    private String fleetPrefix;
    private String fleetNumber;
    private String thumbnail;
    private String username;
    private Long uploadedDate;
    private List<String> tags;
}
