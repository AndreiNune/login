package com.fateczl.entity;

import com.fateczl.entity.enumerable.UserRole;

import java.util.List;

public record User(
        String id,
        String username,
        String password,
        String email,
        List<UserRole> roles
) {
}
