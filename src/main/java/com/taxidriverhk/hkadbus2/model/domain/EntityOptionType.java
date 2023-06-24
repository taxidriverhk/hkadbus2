package com.taxidriverhk.hkadbus2.model.domain;

import java.util.Arrays;

import com.taxidriverhk.hkadbus2.exception.BadRequestException;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum EntityOptionType {

    ADVERTISEMENT("advertisement"),
    BUS_MODEL("bus-model"),
    CATEGORY("category"),
    LICENSE_PLATE_NUMBER("license-plate-number"),
    ROUTE("route");

    @Getter
    private final String name;

    public static EntityOptionType fromName(final String name) {
        return Arrays.stream(EntityOptionType.values())
                .filter(entityType -> entityType.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new BadRequestException("Invalid entity option type"));
    }
}
