package com.fitness.userservice.users.dto;

import com.fitness.userservice.enums.UserEnums.UserRoles;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class UsersResponse {
    
    @Schema(description = "User ID")
    private String id;

    @Schema(description = "User Email")
    private String email;

    @Schema(description = "Username")
    private String username;

    @Schema(description = "User First Name")
    private String firstName;

    @Schema(description = "User Last Name")
    private String lastName;

    @Schema(description = "User Role")
    private UserRoles role;

    @Schema(description = "Record Version")
    private Integer version;

    @Schema(description = "Creation Timestamp")
    private LocalDateTime createdAt;
    
    @Schema(description = "Last Update Timestamp")
    private LocalDateTime updatedAt;

}
