package com.myapp.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.myapp.model.Entity.User;
import com.myapp.model.Repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.Collections;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Fetch all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Fetch a single user by ID
    public List<User> getUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.map(Collections::singletonList).orElseGet(Collections::emptyList);
    }
    

    // Create a new user
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // Update a user
    public User updateUser(Long id, User userDetails) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setName(userDetails.getName());
            user.setEmail(userDetails.getEmail());
            // Update other fields as necessary
            return userRepository.save(user);
        }
        return null;
    }

    // Partially update a user
    public User patchUser(Long id, User userDetails) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (userDetails.getName() != null) user.setName(userDetails.getName());
            if (userDetails.getEmail() != null) user.setEmail(userDetails.getEmail());
            // Update only fields provided
            return userRepository.save(user);
        }
        return null;
    }

    // Delete a user by ID
    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<User> getUsers(int page, int size, String name, String email, Integer id) {
        var user = userRepository.findUserByCriteria(name, email, id, page, size);
        return user;
    }
}
