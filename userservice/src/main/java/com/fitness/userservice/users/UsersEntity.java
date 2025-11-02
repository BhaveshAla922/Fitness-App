package com.fitness.userservice.users;

import com.fitness.userservice.enums.UserEnums.UserRoles;
import com.fitness.userservice.enums.UserEnums.UserStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.EnumType;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import java.time.LocalDateTime;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

@Entity
@Table(name = "users")
@Data
public class UsersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(unique=true, nullable=false, length=64)
    private String email;

    @Column(unique=true, nullable=true, length=64)
    private String username;

    @Column(nullable=false, length=128)
    private String password;

    @Column(nullable=true, length=64)
    private String firstName;

    @Column(nullable=true, length=64)
    private String lastName;

    @Enumerated(EnumType.STRING)
    private UserRoles role;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Version
    private Integer version;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @CreatedBy
    private String createdById;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @LastModifiedBy
    private String updatedById;

}
