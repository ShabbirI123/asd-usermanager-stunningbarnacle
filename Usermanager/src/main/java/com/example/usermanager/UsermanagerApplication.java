package com.example.usermanager;

import com.example.usermanager.database.user.UserController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UsermanagerApplication {

	public static void main(String[] args) {

		SpringApplication.run(UsermanagerApplication.class, args);

		UserController userController = new UserController();
		System.out.println(userController.listAll());
	}

}
