package com.fitness.userservice.users;

import com.fitness.userservice.users.dto.CreateUserRequest;
import com.fitness.userservice.users.dto.UpdateUserRequest;
import com.fitness.userservice.users.dto.UsersResponse;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UsersMapper {

    UsersResponse toUserResponse(UsersEntity user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdById", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "updatedById", ignore = true)
    UsersEntity toUser(CreateUserRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateUser(UpdateUserRequest request, @MappingTarget UsersEntity user);

}
