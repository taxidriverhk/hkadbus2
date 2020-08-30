package com.taxidriverhk.hkadbus2.model.domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SearchPhotoResult {

    private String photoId;
    private String thumbnail;
    private String licensePlateNumber;
    private String fleetNumber;
    private String username;
}
