package com.taxidriverhk.hkadbus2.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Builder(toBuilder = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusModelEntity {

    public static final String NAME = "name";
    public static final String HASH_KEY = "hashKey";
    public static final String BUS_BRAND_ID = "busBrandId";
    public static final String THUMBNAIL = "thumbnail";

    private String hashKey;
    private Map<String, String> name;
    private String busBrandId;
    private String thumbnail;
}
