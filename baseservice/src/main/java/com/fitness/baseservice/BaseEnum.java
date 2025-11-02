package com.fitness.baseservice;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface BaseEnum {

    String getLabel();
    String name();

    public static <E extends Enum<E>> List<String> getEnumValues(Class<E> enumClass) {
        return Arrays.stream(enumClass.getEnumConstants())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    public static <E extends Enum<E> & BaseEnum> List<Map<String, String>> getLabelValues(Class<E> enumClass) {
        return Arrays.stream(enumClass.getEnumConstants())
                .map(e -> Collections.singletonMap(e.getLabel(), e.name()))
                .collect(Collectors.toList());
    }

    class LabelValue {

        public final String label;
        public final String value;

        public LabelValue(String label, String value) {
            this.label = label;
            this.value = value;
        }
        
    }

}
