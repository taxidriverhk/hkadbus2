package com.taxidriverhk.hkadbus2.model.entity.converter;

import java.util.Arrays;
import java.util.List;

import jakarta.persistence.AttributeConverter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class StringListConverter implements AttributeConverter<List<String>, String> {

    private final static String SEPARATOR = ",";

    @Override
    public String convertToDatabaseColumn(final List<String> stringList) {
        return String.join(SEPARATOR, stringList);
    }

    @Override
    public List<String> convertToEntityAttribute(final String serializedList) {
        final String[] items = serializedList.split(SEPARATOR);
        return Arrays.asList(items);
    }
}
