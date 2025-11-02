package com.fitness.userservice.users.dto;

import com.fitness.baseservice.BaseService.PaginationConfig;
import com.fitness.baseservice.decorators.EnumValidator;
import com.fitness.userservice.enums.UserEnums.UserRoles;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class FindAllUsersRequest implements PaginationConfig {

    @Schema(description = "Page number")
    private Integer page;

    @Schema(description = "Number of records per page")
    private Integer limit;

    @Schema(description = "Sort by field")
    private String sortBy;

    @Schema(description = "User ID")
    private String id;

    @Schema(description = "User Email")
    private String email;

    @Schema(description = "Username")
    @Size(max = 64, message = "Username is too long. Please use a shorter username.")
    private String username;

    @Schema(description = "User First Name")
    @Size(max = 64, message = "First name is too long. Please use a nickname or shorter first name.")
    private String firstName;

    @Schema(description = "User Last Name")
    @Size(max = 64, message = "Last name is too long. Please use a shorter last name.")
    private String lastName;

    @Schema(description = "User Role")
    @EnumValidator(enumClass = UserRoles.class, message = "The role you have provided is invalid.")
    private String role;

    @Override
    public String[] getSortableFields() {
        return new String[]{"id", "email", "username", "firstName", "lastName", "role"};
    }

    @Override
    public String[] getFilterableFields() {
        return new String[]{"id", "email", "username", "firstName", "lastName", "role"};
    }

}
