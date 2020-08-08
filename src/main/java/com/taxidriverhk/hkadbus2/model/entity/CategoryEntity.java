package com.taxidriverhk.hkadbus2.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder(toBuilder = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryEntity {

    public static final String NAME = "name";
    public static final String HASH_KEY = "hashKey";
    public static final String THUMBNAIL = "thumbnail";

    private String hashKey;
    private String name;
    private String thumbnail;
}
