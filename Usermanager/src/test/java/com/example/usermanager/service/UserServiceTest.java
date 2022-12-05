package com.example.usermanager.service;

import com.example.usermanager.Exceptions.User.DBInputException;
import com.example.usermanager.model.User;
import com.example.usermanager.repository.UserRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void addUser_username_notInUse() throws DBInputException {
        UserService userService = new UserService(userRepository);

        User user = new User(
                "Shabbir",
                "Islam",
                "shabby",
                "password"
        );


        userService.addUser(user);

        assertEquals(userRepository.findByUsername("shabby").getUsername(), user.getUsername());
    }

    @Test
    void addUser_username_inUse() throws DBInputException {
        UserService userService = new UserService(userRepository);

        User user = new User(
                "Shabbir",
                "Islam",
                "shabby",
                "password"
        );

        assertThrows(DBInputException.class, () -> userService.addUser(user));
    }

    @Test
    void alterPassword() {
    }

    @Test
    void deleteAccount() {
    }

    @AfterAll
    static void tearDown() {
        //TODO: implement tearDown method
    }
}