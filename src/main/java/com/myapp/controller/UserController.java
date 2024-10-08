package com.myapp.controller;

import com.myapp.model.Entity.User;
import com.myapp.response.BaseResponse;
import com.myapp.service.UserService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    // GET all users
    @GetMapping
    public BaseResponse<List<User>> getUsers(
            @RequestParam(defaultValue = "1") int page, 
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) Integer id) {
                logger.info("[UserController.getUsers]");
        try {
            logger.info("Endpoint '/users' hit: page={}, size={}, name={}, email={}, id={}", 
            page, size, name, email, id);
            List<User> users = userService.getUsers(page, size, name, email, id);
            if (users.isEmpty()) {
                return new BaseResponse<>("success", "No users found", users);
            }

            logger.info("UserController.getUsers", users);
            
            return new BaseResponse<>("success", "Users fetched successfully", users);
        } catch (Exception e) {
            logger.error("Error fetching users", e);
            return new BaseResponse<>("error", "Failed to fetch users", null);
        }
    }

    // GET a user by ID
    @GetMapping("/{id}")    
    public BaseResponse<List<User>> getUserById(@PathVariable("id") Long id) {
        logger.info("[UserController.getUserById]");
        
        List<User> users = userService.getUserById(id);
        
        if (users.isEmpty()) {
            logger.info("[UserController.getUserById - error]");
            return new BaseResponse<>("error", "User not found", users);
        }
        
        logger.info("[UserController.getUserById - susccess {}]", users);
        return new BaseResponse<>("success", "User fetched successfully", users);
    }

    // POST - Create a new user
    @PostMapping
      public BaseResponse<User> createUser(@Valid @RequestBody User user) {
        logger.info("[UserController.createUser]");
        try {
            logger.info("Creating a new user with name: {}", user.getName());
            User createdUser = userService.createUser(user);
            return new BaseResponse<>("success", "User created successfully", createdUser);
        } catch (Exception e) {
            logger.error("Error creating user", e);
            return new BaseResponse<>("error", "Failed to create user", null);
        }
    }

    // PUT - Update a user by ID
    @PutMapping("/{id}")
    public BaseResponse<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        logger.info("[UserController.updateUser]");
        try {
            logger.info("Updating user with ID: {}", id);
            User updatedUser = userService.updateUser(id, userDetails);
            if (updatedUser != null) {
                return new BaseResponse<>("success", "User updated successfully", updatedUser);
            } else {
                return new BaseResponse<>("error", "User not found", null);
            }
        } catch (Exception e) {
            logger.error("Error updating user", e);
            return new BaseResponse<>("error", "Failed to update user", null);
        }
    }

    // PATCH - Partially update a user by ID
    @PatchMapping("/{id}")
    public BaseResponse<User> patchUser(@Valid @PathVariable Long id, @RequestBody User userDetails) {
        logger.info("[UserController.patchUser]");
        try {
            logger.info("Partially updating user with ID: {}", id);
            User updatedUser = userService.patchUser(id, userDetails);
            if (updatedUser != null) {
                return new BaseResponse<>("success", "User updated successfully", updatedUser);
            } else {
                return new BaseResponse<>("error", "User not found", null);
            }
        } catch (Exception e) {
            logger.error("Error patching user", e);
            return new BaseResponse<>("error", "Failed to update user", null);
        }
    }

    // DELETE - Delete a user by ID
    @DeleteMapping("/{id}")
    public BaseResponse<String> deleteUser(@PathVariable Long id) {
        logger.info("[UserController.deleteUser]");
        try {
            logger.info("Deleting user with ID: {}", id);
            boolean deleted = userService.deleteUser(id);
            if (deleted) {
                return new BaseResponse<>("success", "User deleted successfully", "User with ID " + id + " deleted.");
            } else {
                return new BaseResponse<>("error", "User not found", null);
            }
        } catch (Exception e) {
            logger.error("Error deleting user", e);
            return new BaseResponse<>("error", "Failed to delete user", null);
        }
    }
}
