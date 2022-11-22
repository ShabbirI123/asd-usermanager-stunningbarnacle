package com.example.usermanager.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired UserRepository repository;

    @GetMapping("/Users")
    public List<User> listAll(){
        return repository.findAll();
    }

    /*@GetMapping("/Users")
    public List<User> getByName(int id){
        return repository.findBy(id);
    }*/
}
