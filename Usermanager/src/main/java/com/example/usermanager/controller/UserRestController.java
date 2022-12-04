package com.example.usermanager.controller;

import com.example.usermanager.Exceptions.User.DBInputException;
import com.example.usermanager.model.User;
import com.example.usermanager.service.UserService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/user")
public class UserRestController {

    private UserService userService;

    @Autowired
    public UserRestController(UserService userService){
        this.userService = userService;
    }



    @PostMapping("/register")
    public void registerUser(@RequestBody User user) throws DBInputException {
        userService.addUser(user);
    }

    @PutMapping( "/edit")
    public void changePassword(@RequestBody ObjectNode objectNode) throws DBInputException {
        userService.alterPassword(objectNode.get("id").asInt(),
                objectNode.get("currentPassword").asText(),
                objectNode.get("newPassword").asText());
    }

    @DeleteMapping("/delete")
    public void deleteUser(@RequestBody ObjectNode objectNode) throws DBInputException {
        userService.deleteAccount(objectNode.get("id").asInt(), objectNode.get("currentPassword").asText());
    }
}
