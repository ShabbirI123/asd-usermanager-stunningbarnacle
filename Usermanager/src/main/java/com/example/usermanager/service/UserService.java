package com.example.usermanager.service;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.example.usermanager.model.User;
import com.example.usermanager.repository.UserRepository;
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
    public void addUser(User user) throws NoSuchAlgorithmException {
        Optional<User> userOptional = Optional.ofNullable(userRepository.findByUsername(user.getUsername()));

        if (userOptional.isPresent()){
            throw new IllegalStateException("Username wird schon verwendet!");
        }

        user.setPassword(UserService.encryptPassword(user.getPassword()));
        userRepository.save(user);
    }


    //TODO login
    public boolean loginUser(String username, String password) throws NoSuchAlgorithmException {
        User user = userRepository.findByUsernameAndPassword(username, UserService.encryptPassword(password));

        if (user == null){
            throw new IllegalStateException("Username oder Passwort ist falsch");
        }

        return true;
    }

    //TODO logout

    //TODO change account information

    //TODO delete account


    //Encryption of the password
    private static String encryptPassword(String password) throws NoSuchAlgorithmException {
        return UserService.toHexString(UserService.getSHA(password));
    }

    //Returns the sha256 value
    public static byte[] getSHA(String input) throws NoSuchAlgorithmException
    {
        /* MessageDigest instance for hashing using SHA256 */
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        /* digest() method called to calculate message digest of an input and return array of byte */
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    //Return a hashed value
    public static String toHexString(byte[] hash)
    {
        /* Convert byte array of hash into digest */
        BigInteger number = new BigInteger(1, hash);

        /* Convert the digest into hex value */
        StringBuilder hexString = new StringBuilder(number.toString(16));

        /* Pad with leading zeros */
        while (hexString.length() < 32)
        {
            hexString.insert(0, '0');
        }

        return hexString.toString();
    }
}
