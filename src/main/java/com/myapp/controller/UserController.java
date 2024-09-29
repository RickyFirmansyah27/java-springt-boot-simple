package com.myapp.controller;

import com.myapp.model.Entity.User;
import com.myapp.response.BaseResponse;
import com.myapp.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping
    public BaseResponse<List<User>> getUsers() {
        logger.info("Fetching all users");
        List<User> users = userService.getAllUsers();
        return new BaseResponse<>("success", "Users fetched successfully", users);
    }

    @PostMapping
    public BaseResponse<User> createUser(@RequestBody User user) {
        logger.info("Creating a new user with name: {}", user.getName());
        User createdUser = userService.createUser(user);
        return new BaseResponse<>("success", "User created successfully", createdUser);
    }
}
