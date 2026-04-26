package com.fateczl.controller.response;

import com.fateczl.entity.enumerable.UserRole;

import java.util.List;

public record UserResponse(
        String id,
        String username,
        String email,
        List<UserRole> roles
) {
}
