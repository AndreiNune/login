package com.fateczl.controller.resquest;

import com.fateczl.entity.enumerable.UserRole;
import java.util.List;

public record UserRequest(
        String username,
        String password,
        String email,
        List<UserRole> roles
) {
}
