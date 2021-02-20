package com.taxidriverhk.hkadbus2.util;

import com.google.common.collect.ImmutableSet;
import com.taxidriverhk.hkadbus2.exception.BadRequestException;
import com.taxidriverhk.hkadbus2.model.domain.SortDirection;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RequestValidator {

    private static final Set<String> VALID_LANGUAGES = ImmutableSet.of("en_us", "zh_hk");
    private static final Set<String> VALID_ORDER_BY = ImmutableSet.of("photoId", "uploadedDate");

    public static void validateLanguage(final String language) {
        if (!VALID_LANGUAGES.contains(language)) {
            throw new BadRequestException(String.format("Invalid language %s", language));
        }
    }

    public static void validateOrderBy(final String orderBy) {
        if (!VALID_ORDER_BY.contains(orderBy)) {
            throw new BadRequestException(String.format("Order by is required and must be an attribute with unique data"));
        }
    }

    public static void validateSort(final String sort) {
        if (!SortDirection.ASC.getName().equals(sort) && !SortDirection.DESC.getName().equals(sort)) {
            throw new BadRequestException(String.format("Invalid sort direction %s", sort));
        }
    }
}
