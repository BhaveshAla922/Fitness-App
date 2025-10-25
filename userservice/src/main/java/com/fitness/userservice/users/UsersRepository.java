package com.fitness.userservice.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<UsersEntity, String> {

    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    
}
