package com.fateczl.controller.resquest;

public record LoginRequest(
        String username,
        String password
) {
}
