package com.example.usermanager.repository;

import com.example.usermanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM User u WHERE u.id = ?1")
    User findByID(Integer id);

    @Query("SELECT u FROM User u WHERE u.username = ?1")
    User findByUsername(String username);
}
