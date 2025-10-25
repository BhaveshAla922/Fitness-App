package com.fitness.userservice.user;

import com.fitness.userservice.user.dto.CreateUserRequest;
import com.fitness.userservice.user.dto.UpdateUserRequest;
import com.fitness.userservice.user.dto.UserResponse;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    // Respositories
    @Autowired
    private UserRepository userRepository;

    // Mappers
    @Autowired
    private UserMapper userMapper;

    public List<UserResponse> getAllUsers() {

        List<UserEntity> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::toUserResponse)
                .toList();

    }

    public UserResponse getUser(String userId) {

        UserEntity user = userRepository.findById(userId)
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

        UserEntity user = userMapper.toUser(userRequest);
        UserEntity createdUser = userRepository.save(user);
        UserResponse userResponse = userMapper.toUserResponse(createdUser);

        return userResponse;

    }

    public UserResponse updateUser(String userId, UpdateUserRequest userRequest) {
        UserEntity existingUser = userRepository.findById(userId)
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

        UserEntity updatedUser = userRepository.save(existingUser);
        return userMapper.toUserResponse(updatedUser);
    }

    public void deleteUser(String userId) {
        UserEntity existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(existingUser);
    }

}
