package com.taxidriverhk.hkadbus2.model.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum SortDirection {

    ASC("asc"),
    DESC("desc");

    @Getter
    private final String name;
}
