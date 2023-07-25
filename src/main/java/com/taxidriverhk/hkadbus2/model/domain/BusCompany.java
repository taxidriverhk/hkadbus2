package com.taxidriverhk.hkadbus2.model.domain;

import java.util.Arrays;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BusCompany {

    KMB("kmb"),
    CMB("cmb"),
    CTB("ctb"),
    LWB("lwb"),
    NLB("nlb"),
    NWFB("nwfb");

    private String text;

    public static BusCompany fromString(final String text) {
        return Arrays.stream(BusCompany.values())
                .filter(busCompany -> busCompany.getText().equalsIgnoreCase(text))
                .findFirst()
                .orElseThrow(() -> new EnumConstantNotPresentException(BusCompany.class, text));
    }
}
