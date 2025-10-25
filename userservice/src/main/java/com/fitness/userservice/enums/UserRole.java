package com.fitness.userservice.enums;

import com.fitness.userservice.base.BaseEnum;

public enum UserRole implements BaseEnum {
    STANDARD_USER("Standard User"),
    ADMINISTRATOR("Administrator");

    private final String label;

    UserRole(String label) {
        this.label = label;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public String getValue() {
        return name();
    }
}
