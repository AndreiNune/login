package com.fateczl.controller;


import com.fateczl.controller.adapter.UserControllerAdapter;
import com.fateczl.controller.response.UserResponse;
import com.fateczl.controller.resquest.UserRequest;
import com.fateczl.entity.User;
import com.fateczl.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class UserController {
    private UserRepository respository;

    public UserController(UserRepository respository){
        this.respository = respository;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/v1/user/save")
    public UserResponse save(@RequestBody UserRequest request){
        User save = respository.save(UserControllerAdapter.cast(request));
        return new UserResponse(
                save.id(),
                save.username(),
                save.email(),
                save.roles());
    }
}
