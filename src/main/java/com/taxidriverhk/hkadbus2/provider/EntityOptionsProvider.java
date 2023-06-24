package com.taxidriverhk.hkadbus2.provider;

import java.util.Map;

import com.taxidriverhk.hkadbus2.model.domain.EntityOptionType;

public interface EntityOptionsProvider {

    Map<String, String> getEntityOptions(EntityOptionType entityType, String language);
}
