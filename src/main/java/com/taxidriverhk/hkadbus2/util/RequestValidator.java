package com.taxidriverhk.hkadbus2.util;

import com.google.common.collect.ImmutableSet;
import com.taxidriverhk.hkadbus2.exception.BadRequestException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RequestValidator {

    private static final Set<String> VALID_LANGUAGES = ImmutableSet.of("en_us", "zh_hk");

    public static void validateLanguage(final String language) {
        if (!VALID_LANGUAGES.contains(language)) {
            throw new BadRequestException(String.format("Invalid language %s", language));
        }
    }
}
