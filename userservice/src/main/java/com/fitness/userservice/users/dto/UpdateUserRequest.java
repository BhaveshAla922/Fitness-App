package com.fitness.userservice.users.dto;

import com.fitness.userservice.base.decorators.EnumValidator;
import com.fitness.userservice.enums.UserEnums.UserRoles;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateUserRequest {

    @Schema(description = "User Email")
    @Email(message = "Invalid email format")
    private String email;

    @Schema(description = "Username")
    @Size(max = 64, message = "Username is too long. Please use a shorter username.")
    private String username;

    @Schema(description = "Password")
    @Size(max = 128, message = "Password is too long. Please use a shorter password.")
    private String password;

    @Schema(description = "User First Name")
    @Size(max = 64, message = "First name is too long. Please use a nickname or shorter first name.")
    private String firstName;

    @Schema(description = "User Last Name")
    @Size(max = 64, message = "Last name is too long. Please use a shorter last name.")
    private String lastName;

    @Schema(description = "User Role")
    @EnumValidator(enumClass = UserRoles.class, message = "The role you have provided is invalid.")
    private String role;
    
}
