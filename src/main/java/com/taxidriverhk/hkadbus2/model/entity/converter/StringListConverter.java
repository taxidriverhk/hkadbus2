package com.taxidriverhk.hkadbus2.model.entity.converter;

import lombok.NoArgsConstructor;

import javax.persistence.AttributeConverter;
import java.util.Arrays;
import java.util.List;

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
