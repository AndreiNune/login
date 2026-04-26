package com.fateczl.controller;

import com.fateczl.controller.adapter.AuthControllerAdapter;
import com.fateczl.controller.response.AuthResponse;
import com.fateczl.controller.resquest.LoginRequest;
import com.fateczl.entity.Token;
import com.fateczl.security.TokenSecurity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AuthController {
    private final TokenSecurity tokenSecurity;

    public AuthController(TokenSecurity tokenSecurity) {
        this.tokenSecurity = tokenSecurity;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/auth")
    public AuthResponse login(@RequestBody LoginRequest request) {
        Token token = tokenSecurity.gerarToken(AuthControllerAdapter.cast(request));
        return new AuthResponse(token.value());
    }
}