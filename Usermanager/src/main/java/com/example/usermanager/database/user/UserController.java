package com.example.usermanager.database.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/login")
    public boolean loginUser(@RequestParam String username, @RequestParam String password){
        return userService.loginUser(username, password);
    }

    @PostMapping()
    public void registerUser(@RequestBody User user){
        userService.addUser(user);
    }
}
