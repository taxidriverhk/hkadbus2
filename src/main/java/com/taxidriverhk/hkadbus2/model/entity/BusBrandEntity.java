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
public class BusBrandEntity {

    public static final String NAME = "name";
    public static final String HASH_KEY = "hashKey";

    private String hashKey;
    private Map<String, String> name;
}
