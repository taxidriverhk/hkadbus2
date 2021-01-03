package com.taxidriverhk.hkadbus2.model.entity.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.NoArgsConstructor;

import javax.persistence.AttributeConverter;
import java.lang.reflect.Type;
import java.util.Map;

@NoArgsConstructor
public class LocalizedStringConverter implements AttributeConverter<Map<String, String>, String> {

    private final static Gson GSON = new Gson();

    @Override
    public String convertToDatabaseColumn(Map<String, String> localizedStringMap) {
        return GSON.toJson(localizedStringMap);
    }

    @Override
    public Map<String, String> convertToEntityAttribute(String serializedMap) {
        final Type mapType = new TypeToken<Map<String, String>>(){}.getType();
        return GSON.fromJson(serializedMap, mapType);
    }
}
