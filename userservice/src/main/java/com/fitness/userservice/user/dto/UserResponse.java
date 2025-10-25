package com.fitness.userservice.user.dto;

import com.fitness.userservice.enums.UserRole;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class UserResponse {
    
    private String id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private UserRole role;
    private int version;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
