package com.fitness.userservice.users;

import com.fitness.userservice.base.ApiVersioning;
import com.fitness.userservice.users.dto.CreateUserRequest;
import com.fitness.userservice.users.dto.FindAllUsersRequest;
import com.fitness.userservice.users.dto.PaginatedUsersResponse;
import com.fitness.userservice.users.dto.UpdateUserRequest;
import com.fitness.userservice.users.dto.UsersResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@Tag(name = "Users")
@ApiVersioning
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersService userService;

    @GetMapping()
    @Operation(summary = "Get All Users", description = "Retrieve a list of users.")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of users")
    public ResponseEntity<PaginatedUsersResponse> getAllUsers(@ParameterObject @Valid @ModelAttribute FindAllUsersRequest userRequest) {
        return ResponseEntity.ok(userService.getAllUsers(userRequest));
    }

    @GetMapping("/{userId}")
    @Operation(summary = "Get User by ID", description = "Retrieve a user.")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of user")
    @ApiResponse(responseCode = "404", description = "User not found")
    public ResponseEntity<UsersResponse> getUser(@PathVariable String userId) {
        return ResponseEntity.ok(userService.getUser(userId));
    }

    @PostMapping()
    @Operation(summary = "Create User", description = "Create a new user.")
    @ApiResponse(responseCode = "201", description = "User created successfully")
    public ResponseEntity<UsersResponse> createUser(@Valid @RequestBody CreateUserRequest userRequest) {
        return ResponseEntity.ok(userService.createUser(userRequest));
    }

    @PutMapping("/{userId}")
    @Operation(summary = "Update User by ID", description = "Update an existing user.")
    public ResponseEntity<UsersResponse> updateUser(@PathVariable String userId,
            @Valid @RequestBody UpdateUserRequest userRequest) {
        return ResponseEntity.ok(userService.updateUser(userId, userRequest));
    }

    @DeleteMapping("/{userId}")
    @Operation(summary = "Delete User by ID", description = "Delete a user.")
    public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

}
