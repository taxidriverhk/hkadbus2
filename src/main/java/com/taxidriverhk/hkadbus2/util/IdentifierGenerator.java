package com.taxidriverhk.hkadbus2.util;

import java.math.BigInteger;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class IdentifierGenerator {

    public static Long generateNumericIdentifier(final String uuid) {
        final String uuidWithoutDashes = uuid.replaceAll("-", "");
        // UUID without dashes is a number in hexadecimal format
        final BigInteger bigNumber = new BigInteger(uuidWithoutDashes, 16);

        final String numericIdString = bigNumber.toString();
        final int digits = numericIdString.length();
        // Obtain the last 18 digits
        final String shortenNumericId = numericIdString.substring(digits - 18, digits);

        return Long.valueOf(shortenNumericId);
    }
}
