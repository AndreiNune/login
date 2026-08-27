package com.fateczl.security;

import com.fateczl.entity.User;
import com.fateczl.repository.UserRepository;
import com.fateczl.security.dto.AuthUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsSecurity implements UserDetailsService{

    private final UserRepository repository;

    public UserDetailsSecurity (UserRepository repository){
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username);
        return new AuthUserDetails(user);
    }
}
