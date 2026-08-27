package com.fateczl.controller;

import com.fateczl.controller.adapter.AuthControllerAdapter;
import com.fateczl.controller.response.AuthResponse;
import com.fateczl.controller.resquest.LoginRequest;
import com.fateczl.entity.Token;
import com.fateczl.entity.User;
import com.fateczl.security.TokenSecurity;
import com.fateczl.security.dto.AuthUserDetails;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
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

    @PostMapping("/v1/auth")
    public void login(@RequestBody LoginRequest request, HttpServletResponse response){
        AuthUserDetails userDetails = tokenSecurity.autenticar(AuthControllerAdapter.cast(request));
        Token token = tokenSecurity.gerarToken(userDetails);
        User user = userDetails.user();
        ResponseCookie cookie = ResponseCookie.from("token", token.value())
                .httpOnly(true)
                .secure(true)
                .sameSite("Strict")
                .path("/")
                .maxAge(jwtSecurity.getExpirationSeconds())
                .build();
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        // return new AuthClaimsResponse(
        //     user.id(),
        //     user.username(),
        //     user.roles().stream().map(Enum::name).toList());
    }
}