package com.fitness.userservice.user;

import com.fitness.userservice.enums.UserRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

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
    private UserRole role;

    @Version
    private int version;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
