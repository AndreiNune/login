package com.fateczl.controller.adapter;

import com.fateczl.controller.resquest.UserRequest;
import com.fateczl.entity.User;

import java.util.UUID;

public class UserControllerAdapter {
    private UserControllerAdapter(){
    }

    public static User cast(UserRequest request){
        return new User(
                UUID.randomUUID().toString(),
                request.username(),
                request.password(),
                request.email(),
                request.roles());
    }
}
