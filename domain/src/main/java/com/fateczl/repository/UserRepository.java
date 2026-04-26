package com.fateczl.repository;

import com.fateczl.entity.User;

public interface UserRepository {
    User save(User user);

    User findByUsername(String user);
}
