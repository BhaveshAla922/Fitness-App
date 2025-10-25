package com.fitness.userservice.user;

import com.fitness.userservice.user.dto.CreateUserRequest;
import com.fitness.userservice.user.dto.UpdateUserRequest;
import com.fitness.userservice.user.dto.UserResponse;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponse toUserResponse(UserEntity user);
    UserEntity toUser(CreateUserRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE)
    void updateUser(UpdateUserRequest request, @MappingTarget UserEntity user);
    
}
