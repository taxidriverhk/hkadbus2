package com.taxidriverhk.hkadbus2.model.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum BusCompany {

    KMB("kmb"),
    CTB("ctb"),
    NWFB("nwfb"),
    CMB("cmb"),
    NLB("nlb");

    private String text;

    public static BusCompany fromString(final String text) {
        return Arrays.stream(BusCompany.values())
                .filter(busCompany -> busCompany.getText().equalsIgnoreCase(text))
                .findFirst()
                .orElseThrow(() -> new EnumConstantNotPresentException(BusCompany.class, text));
    }
}
