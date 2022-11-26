package com.example.usermanager.service;

import java.security.NoSuchAlgorithmException;

import com.example.usermanager.model.User;
import com.example.usermanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    /*
    * All the logic for the application is here
    * We have:
    *   Create account
    *   Login
    *   Logout
    *   Change account information
    *   Delete account
    * */

    public UserService(){

    }
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    //TODO create account
    public void addUser(User user) throws Exception {
        User user1 = userRepository.findByUsername(user.getUsername());

        if (user1 != null){
            throw new IllegalStateException("Username wird schon verwendet!");
        }

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        userRepository.save(user);
    }

    //TODO change account information

    //TODO delete account
}
