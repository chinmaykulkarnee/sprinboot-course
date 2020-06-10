package com.upgrad.course.service;

import com.upgrad.course.entity.User;
import com.upgrad.course.repository.UserRepository;

import java.util.Optional;

// TODO: Mark this class a service using using correct annotation
public class UserService {

    private UserRepository userRepository;

    // TODO: Autowire user repository here to get access to its methods


    // TODO: Use repository to get user by name
    public Optional<User> getUserByName(String name) {
        return Optional.empty();
    }

    // TODO: Use repository to get user by name and email
    public Optional<User> getUserByNameAndEmail(String name, String email) {
        return Optional.empty();
    }

    public void addUser(String name, String email) {
        this.userRepository.save(new User(name, email));
    }
}
