package com.fitness.userservice.enums;

import com.fitness.userservice.base.BaseEnum;

public class UserEnums {

    // Roles dictate what services a user can access
    public enum UserRoles implements BaseEnum {
        SUPER_ADMINISTRATOR("Super Administrator"),
        COMPANY_REPRESENTATIVE("Company Representative"),
        BRANCH_MANAGER("Branch Manager"),
        GYM_MANAGER("Gym Manager"),
        TRAINER("Trainer"),
        STANDARD_USER("Standard User"),
        GUEST_USER("Guest User"),
        SUPPORT_STAFF("Support Staff"),
        ACCOUNTANT("Accountant"),
        SALES_REPRESENTATIVE("Sales Representative"),
        EXTERNAL_PARTNER("External Partner");

        private final String label;

        UserRoles(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }
    }
    
}
