package com.example.usermanager.database.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    //TODO create account
    public void addUser(User user) {
        Optional<User> userOptional = userRepository.findByUsername(user.getUsername());

        if (userOptional.isPresent()){
            throw new IllegalStateException("Username wird schon verwendet!");
        }

        userRepository.save(user);
    }


    //TODO login
    public boolean loginUser(String username, String password) {
        Optional<User> userOptional = userRepository.findByUsernameAndPassword(username, password);

        if (!userOptional.isPresent()){
            throw new IllegalStateException("Username oder Passwort ist falsch");
        }

        return true;
    }

    //TODO logout

    //TODO change account information

    //TODO delete account
}
