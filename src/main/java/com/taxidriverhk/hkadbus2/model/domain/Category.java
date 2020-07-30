package com.taxidriverhk.hkadbus2.model.domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Category {

    private String id;
    private String name;
    private String thumbnail;
}
