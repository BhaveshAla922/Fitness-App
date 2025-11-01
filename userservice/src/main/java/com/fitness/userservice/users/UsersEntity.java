package com.fitness.userservice.users;

import com.fitness.userservice.base.BaseEntity;
import com.fitness.userservice.enums.UserEnums.UserRoles;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class UsersEntity extends BaseEntity {

    @Column(unique=true, nullable=false, length=64)
    private String email;

    @Column(unique=true, nullable=false, length=64)
    private String username;

    @Column(nullable=false, length=128)
    private String password;

    @Column(nullable=false, length=64)
    private String firstName;

    @Column(nullable=false, length=64)
    private String lastName;

    @Enumerated(EnumType.STRING)
    private UserRoles role;

}
