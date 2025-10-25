package com.fitness.userservice.base;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public interface BaseEnum {
    String getLabel();
    String getValue();

    class LabelValue {
        private final String label;
        private final String value;

        public LabelValue(String label, String value) {
            this.label = label;
            this.value = value;
        }

        public String getLabel() {
            return label;
        }

        public String getValue() {
            return value;
        }
    }

    // Get all enum values as a list
    static <E extends Enum<E>> List<E> getEnumValues(Class<E> enumClass) {
        return Arrays.asList(enumClass.getEnumConstants());
    }

    // Get label-value pairs for enums
    static <E extends Enum<E> & BaseEnum> List<LabelValue> getLabelValues(Class<E> enumClass) {
        return Arrays.stream(enumClass.getEnumConstants())
                .map(e -> new LabelValue(e.getLabel(), e.getValue()))
                .collect(Collectors.toList());
    }
}