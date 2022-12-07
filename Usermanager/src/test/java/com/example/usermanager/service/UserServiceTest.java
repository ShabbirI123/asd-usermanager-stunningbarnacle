package com.example.usermanager.service;

import com.example.usermanager.Exceptions.User.DBInputException;
import com.example.usermanager.model.User;
import com.example.usermanager.repository.UserRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    UserService userService = new UserService(userRepository);

    User user = getUser();

    @Test
    void addUser_username_notInUse() throws DBInputException {
        userService.addUser(user);

        assertEquals(userRepository.findByUsername("shabbysama").getUsername(), user.getUsername());
    }

    @Test
    void addUser_username_inUse() {
        assertThrows(DBInputException.class, () -> userService.addUser(user));
    }

    //TODO: figure out why password stuff fails
    @Test
    void alterPassword() throws DBInputException {
        userService.alterPassword(user.getId(), user.getPassword(), "test");

        assertEquals("test", user.getPassword());

    }

    @Test
    void deleteAccount() throws DBInputException {
        String password = user.getPassword();
        userService.deleteAccount(user.getId(), password);

        assertNull(userRepository.findByUsername(user.getUsername()));

    }

    @AfterAll
    static void tearDown() {
        //TODO: implement tearDown method
    }

    private User getUser() {
        return new User(
                4,
                "Shabbir",
                "Islam",
                "shabbysama",
                "password"
        );
    }
}