package com.fitness.userservice.base.enums;

import com.fitness.userservice.base.BaseEnum;

public class CommonEnums {

    public enum Status implements BaseEnum {
        ACTIVE("Active"),
        INACTIVE("Inactive"),
        DELETED("Deleted");

        private final String label;

        Status(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }
    }
    
}
