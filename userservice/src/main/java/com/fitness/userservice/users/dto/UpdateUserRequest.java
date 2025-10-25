package com.fitness.userservice.users.dto;

import com.fitness.userservice.enums.EnumValidator;
import com.fitness.userservice.enums.UserRole;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateUserRequest {

    @Email(message = "Invalid email format")
    private String email;

    @Size(max = 64, message = "Username is too long. Please use a shorter username.")
    private String username;

    @Size(max = 128, message = "Password is too long. Please use a shorter password.")
    private String password;

    @Size(max = 64, message = "First name is too long. Please use a nickname or shorter first name.")
    private String firstName;

    @Size(max = 64, message = "Last name is too long. Please use a shorter last name.")
    private String lastName;

    @EnumValidator(enumClass = UserRole.class, message = "The role you have provided is invalid.")
    private String role;
    
}
