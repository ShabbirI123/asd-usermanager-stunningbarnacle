package com.example.usermanager.controller;

import com.example.usermanager.model.User;
import com.example.usermanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("api/v1/user")
public class UserRestController {

    private UserService userService;

    @Autowired
    public UserRestController(UserService userService){
        this.userService = userService;
    }



    @PostMapping("/register")
    public void registerUser(@RequestBody User user) throws Exception {
        userService.addUser(user);
    }

    @PutMapping(path = "{id}")
    public void changePassword(){

    }

    @DeleteMapping(path = "{id}")
    public void deleteUser(){

    }
}
