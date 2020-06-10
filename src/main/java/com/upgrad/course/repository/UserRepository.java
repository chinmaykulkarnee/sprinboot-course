package com.upgrad.course.repository;

import com.upgrad.course.entity.User;
import org.springframework.data.repository.CrudRepository;

// TODO: Mark this class a repository using using correct annotation
public interface UserRepository extends CrudRepository<User, Integer> {

    // TODO: Define a new method to find a user by name and return Optional<User>

    // TODO: Define a new method to find a user by name and email, and return Optional<User>
}
