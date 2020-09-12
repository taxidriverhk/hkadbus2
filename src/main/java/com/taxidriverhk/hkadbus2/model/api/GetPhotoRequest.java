package com.taxidriverhk.hkadbus2.model.api;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GetPhotoRequest {

    private String photoId;
    private String language;
}
