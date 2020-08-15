package com.taxidriverhk.hkadbus2.model.api;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GetAdvertisementsRequest {

    private String categoryId;
    private String language;
}
