package com.fitness.userservice.users;

import com.fitness.userservice.users.dto.CreateUserRequest;
import com.fitness.userservice.users.dto.UpdateUserRequest;
import com.fitness.userservice.users.dto.UserResponse;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    // Respositories
    @Autowired
    private UsersRepository userRepository;

    // Mappers
    @Autowired
    private UsersMapper userMapper;

    public List<UserResponse> getAllUsers() {

        List<UsersEntity> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::toUserResponse)
                .toList();

    }

    public UserResponse getUser(String userId) {

        UsersEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserResponse userResponse = userMapper.toUserResponse(user);

        return userResponse;

    }

    public UserResponse createUser(CreateUserRequest userRequest) {

        if (userRepository.existsByEmail(userRequest.getEmail())) {
            throw new IllegalArgumentException("Email already exists. Please login using " + userRequest.getEmail()
                    + " or use a different email to register.");
        }

        if (userRepository.existsByUsername(userRequest.getUsername())) {
            throw new IllegalArgumentException("Username already exists. Please choose a different username.");
        }

        UsersEntity user = userMapper.toUser(userRequest);
        UsersEntity createdUser = userRepository.save(user);
        UserResponse userResponse = userMapper.toUserResponse(createdUser);

        return userResponse;

    }

    public UserResponse updateUser(String userId, UpdateUserRequest userRequest) {
        UsersEntity existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (userRepository.existsByEmail(userRequest.getEmail()) && !userRequest.getEmail().equals(existingUser.getEmail())) {
            throw new IllegalArgumentException("Email already exists. Please login using " + userRequest.getEmail()
                    + " or use a different email to register.");
        }

        if (userRepository.existsByUsername(userRequest.getUsername()) && !userRequest.getUsername().equals(existingUser.getUsername())) {
            throw new IllegalArgumentException("Username already exists. Please choose a different username.");
        }

        // Use MapStruct to update only non-null fields
        userMapper.updateUser(userRequest, existingUser);

        UsersEntity updatedUser = userRepository.save(existingUser);
        return userMapper.toUserResponse(updatedUser);
    }

    public void deleteUser(String userId) {
        UsersEntity existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(existingUser);
    }

}
