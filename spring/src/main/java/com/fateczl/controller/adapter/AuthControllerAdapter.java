package com.fateczl.controller.adapter;

import com.fateczl.controller.resquest.LoginRequest;
import com.fateczl.entity.Login;

public class AuthControllerAdapter {
    private AuthControllerAdapter() {
    }

    public static Login cast(LoginRequest request) {
        return new Login(request.username(), request.password());
    }
}