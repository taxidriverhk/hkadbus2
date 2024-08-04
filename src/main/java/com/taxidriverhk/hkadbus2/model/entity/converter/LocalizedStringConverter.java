package com.taxidriverhk.hkadbus2.model.entity.converter;

import java.lang.reflect.Type;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import jakarta.persistence.AttributeConverter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class LocalizedStringConverter implements AttributeConverter<Map<String, String>, String> {

    private final static Gson GSON = new Gson();

    @Override
    public String convertToDatabaseColumn(final Map<String, String> localizedStringMap) {
        return GSON.toJson(localizedStringMap);
    }

    @Override
    public Map<String, String> convertToEntityAttribute(final String serializedMap) {
        final Type mapType = new TypeToken<Map<String, String>>(){}.getType();
        return GSON.fromJson(serializedMap, mapType);
    }
}
