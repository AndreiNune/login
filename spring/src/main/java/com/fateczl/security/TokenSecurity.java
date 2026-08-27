package com.fateczl.security;

import com.fateczl.entity.Token;
import com.fateczl.security.dto.AuthUserDetails;
import com.fateczl.entity.Login;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class TokenSecurity {
    private final JwtSecurity jwtSecurity;
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;

    public TokenSecurity(JwtSecurity jwtSecurity, UserDetailsService userDetailsService, AuthenticationManager authenticationManager){
        this.jwtSecurity = jwtSecurity;
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
    }

    public Token gerarToken(Login login){
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                login.username(), login.password());
        authenticationManager.authenticate((authToken));
        UserDetails userDetails = userDetailsService.loadUserByUsername(login.username());
        return new Token(jwtSecurity.generateToken(userDetails));
    }
    
    public AuthUserDetails autenticar(Login login){
        UsernamePasswordAuthenticationToken authToken = 
            new UsernamePasswordAuthenticationToken(
                login.username(),
                login.password());
               authenticationManager.authenticate(authToken);
            return (AuthUserDetails) userDetailsService
            .loadUserByUsername(login.username()); 
    }
}
