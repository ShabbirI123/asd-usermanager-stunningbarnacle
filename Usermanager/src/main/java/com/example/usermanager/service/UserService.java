package com.example.usermanager.service;

import com.example.usermanager.Exceptions.User.DBInputException;
import com.example.usermanager.model.User;
import com.example.usermanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    public void alterPassword(int id, String currentPassword, String newPassword) throws DBInputException {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        User user = userRepository.findByID(id);

        if (user == null){
            throw new DBInputException(id + " " + bCryptPasswordEncoder.encode(currentPassword) + " " +newPassword, null);
        }

        if (!bCryptPasswordEncoder.matches(currentPassword, user.getPassword())){
            throw new DBInputException("Passwort ist nicht korrekt", null);
        }

        user.setPassword(bCryptPasswordEncoder.encode(newPassword));

        userRepository.save(user);
    }

    //TODO delete account
    public void deleteAccount(int id, String currentPassword) throws DBInputException {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        User user = userRepository.findByID(id);

        if (user == null){
            throw new DBInputException("Passwort stimmt nicht!", null);
        }

        if (!bCryptPasswordEncoder.matches(currentPassword, user.getPassword())){
            throw new DBInputException("Passwort ist nicht korrekt", null);
        }

        userRepository.delete(user);
    }
}
