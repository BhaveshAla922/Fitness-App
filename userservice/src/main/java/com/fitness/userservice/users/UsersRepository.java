package com.fitness.userservice.users;

import com.fitness.userservice.base.BaseRepository;

public interface UsersRepository extends BaseRepository<UsersEntity, String> {

    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    
}
