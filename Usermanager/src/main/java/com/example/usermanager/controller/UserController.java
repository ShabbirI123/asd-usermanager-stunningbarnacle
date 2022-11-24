package com.example.usermanager.controller;

import com.example.usermanager.model.User;
import com.example.usermanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/login")
    public boolean loginUser(@RequestParam String username, @RequestParam String password) throws NoSuchAlgorithmException {
        return userService.loginUser(username, password);
    }

    @PostMapping("/register")
    public void registerUser(@RequestBody User user) throws NoSuchAlgorithmException {
        userService.addUser(user);
    }

    @PutMapping(path = "{id}")
    public void changePassword(){

    }

    @DeleteMapping(path = "{id}")
    public void deleteUser(){

    }
}
