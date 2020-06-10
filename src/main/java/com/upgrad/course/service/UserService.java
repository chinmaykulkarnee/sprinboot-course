package com.upgrad.course.service;

import com.upgrad.course.entity.User;
import com.upgrad.course.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

// TODO: Mark this class a service using using correct annotation
@Service
public class UserService {

    private UserRepository userRepository;

    // TODO: Autowire user repository here to get access to its methods
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    // TODO: Use repository to get user by name
    public Optional<User> getUserByName(String name) {
        return this.userRepository.findByName(name);
    }

    // TODO: Use repository to get user by name and email
    public Optional<User> getUserByNameAndEmail(String name, String email) {
        return this.userRepository.findByNameAndEmail(name, email);
    }

    public void addUser(String name, String email) {
        this.userRepository.save(new User(name, email));
    }
}
