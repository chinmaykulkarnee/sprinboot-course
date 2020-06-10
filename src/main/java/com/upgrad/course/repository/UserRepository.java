package com.upgrad.course.repository;

import com.upgrad.course.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// TODO: Mark this class a repository using using correct annotation
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    // TODO: Define a new method to find a user by name and return Optional<User>
    Optional<User> findByName(String name);

    // TODO: Define a new method to find a user by name and email, and return Optional<User>
    Optional<User> findByNameAndEmail(String name, String email);
}
