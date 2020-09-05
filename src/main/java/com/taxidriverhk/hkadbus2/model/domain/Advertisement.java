package com.taxidriverhk.hkadbus2.model.domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Advertisement {

    private String id;
    private String name;
    private String categoryId;
    private String categoryName;
    private String thumbnail;
}
