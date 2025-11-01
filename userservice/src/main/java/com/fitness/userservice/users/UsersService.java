package com.fitness.userservice.users;

import com.fitness.userservice.base.BaseService;
import com.fitness.userservice.users.dto.CreateUserRequest;
import com.fitness.userservice.users.dto.FindAllUsersRequest;
import com.fitness.userservice.users.dto.PaginatedUsersResponse;
import com.fitness.userservice.users.dto.UpdateUserRequest;
import com.fitness.userservice.users.dto.UsersResponse;

import java.lang.reflect.Field;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class UsersService extends BaseService {

    // Respositories
    @Autowired
    private UsersRepository userRepository;

    // Mappers
    @Autowired
    private UsersMapper userMapper;

    public PaginatedUsersResponse getAllUsers(FindAllUsersRequest userRequest) {
        PaginateResult<UsersEntity> paginateResult =
            paginate(UsersEntity.class, userRequest);

        Page<UsersEntity> page = userRepository.findAll(paginateResult.specification, paginateResult.pageable);

        PaginatedUsersResponse response = new PaginatedUsersResponse();
        response.setData(page.getContent().stream().map(userMapper::toUserResponse).toList());
        response.setPageNumber(page.getNumber() + 1);
        response.setPageSize(page.getSize());
        response.setTotalRecords(page.getTotalElements());

        return response;
    }

    public UsersResponse getUser(String userId) {

        UsersEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        UsersResponse userResponse = userMapper.toUserResponse(user);

        return userResponse;

    }

    public UsersResponse getUserByOptions(String fieldName, String fieldValue) {
        UsersEntity probe = new UsersEntity();
        
        try {
            Field field = UsersEntity.class.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(probe, fieldValue);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new IllegalArgumentException("Unsupported field: " + fieldName);
        }

        UsersEntity user = userRepository.findOne(Example.of(probe))
                .orElseThrow(() -> new RuntimeException("User not found"));

        return userMapper.toUserResponse(user);
    }

    public UsersResponse createUser(CreateUserRequest userRequest) {

        if (userRepository.existsByEmail(userRequest.getEmail())) {
            throw new IllegalArgumentException("Email already exists. Please login using " + userRequest.getEmail()
                    + " or use a different email to register.");
        }

        if (userRepository.existsByUsername(userRequest.getUsername())) {
            throw new IllegalArgumentException("Username already exists. Please choose a different username.");
        }

        UsersEntity user = userMapper.toUser(userRequest);
        UsersEntity createdUser = userRepository.save(user);
        UsersResponse userResponse = userMapper.toUserResponse(createdUser);

        return userResponse;

    }

    public UsersResponse updateUser(String userId, UpdateUserRequest userRequest) {
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
        UsersResponse userResponse = userMapper.toUserResponse(updatedUser);

        return userResponse;
    }

    public void deleteUser(String userId) {
        UsersEntity existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(existingUser);
    }

}
