package com.fitness.activityservice.enums;

import com.fitness.baseservice.BaseEnum;

public class ActivityEnums {

    public enum ActivityIntensity implements BaseEnum {
        LOW("Low"),
        MEDIUM("Medium"),
        HIGH("High");

        private final String label;

        ActivityIntensity(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }
    }

    public enum ActivityStatus implements BaseEnum {
        PLANNED("Planned"),
        IN_PROGRESS("In Progress"),
        COMPLETED("Completed"),
        CANCELLED("Cancelled");

        private final String label;

        ActivityStatus(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }
    }

    public enum ActivityTypeStatus implements BaseEnum {
        ACTIVE("Active"),
        INACTIVE("Inactive");

        private final String label;

        ActivityTypeStatus(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }
    }
    
}
