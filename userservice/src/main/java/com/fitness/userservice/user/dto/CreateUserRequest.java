package com.fitness.userservice.user.dto;

import com.fitness.userservice.enums.UserRole;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateUserRequest {

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Username is required")
    @Size(max = 64, message = "Username is too long. Please use a shorter username.")
    private String username;
    
    @NotBlank(message = "Password is required")
    @Size(max = 128, message = "Password is too long. Please use a shorter password.")
    private String password;

    @Size(max = 64, message = "First name is too long. Please use a nickname or shorter first name.")
    private String firstName;

    @Size(max = 64, message = "Last name is too long. Please use a shorter last name.")
    private String lastName;

    @NotNull(message = "Role needs to be assigned for the user.")
    private UserRole role;
    
}
