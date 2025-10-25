package com.fitness.userservice.users;

import com.fitness.userservice.users.dto.CreateUserRequest;
import com.fitness.userservice.users.dto.UpdateUserRequest;
import com.fitness.userservice.users.dto.UserResponse;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UsersMapper {

    UserResponse toUserResponse(UsersEntity user);

    @org.mapstruct.Mapping(target = "id", ignore = true)
    @org.mapstruct.Mapping(target = "version", ignore = true)
    @org.mapstruct.Mapping(target = "createdAt", ignore = true)
    @org.mapstruct.Mapping(target = "updatedAt", ignore = true)
    UsersEntity toUser(CreateUserRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE)
    @org.mapstruct.Mapping(target = "id", ignore = true)
    @org.mapstruct.Mapping(target = "version", ignore = true)
    @org.mapstruct.Mapping(target = "createdAt", ignore = true)
    @org.mapstruct.Mapping(target = "updatedAt", ignore = true)
    void updateUser(UpdateUserRequest request, @MappingTarget UsersEntity user);

}
