package com.upgrad.course;

import com.upgrad.course.entity.User;
import com.upgrad.course.repository.UserRepository;
import com.upgrad.course.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class SpringbootApplicationTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void clean() {
        userRepository.deleteAll();
    }

    @Test
    void contextLoads() {
    }

    @Test
    void shouldGetUserByName() {
        userService.addUser("user1", "user1@users.com");
        Optional<User> mayBeUser = userService.getUserByName("user1");
        Assertions.assertTrue(mayBeUser.isPresent());
        User user = mayBeUser.get();
        Assertions.assertEquals("user1", user.getName());
        Assertions.assertEquals("user1@users.com", user.getEmail());
    }

    @Test
    void shouldReturnEmptyWhenUserNotPresentForGivenName() {
        Optional<User> mayBeUser = userService.getUserByName("user2");
        Assertions.assertFalse(mayBeUser.isPresent());
    }

    @Test
    void shouldGetUserByNameAndEmail() {
        userService.addUser("user3", "user3@users.com");
        Optional<User> mayBeUser = userService.getUserByNameAndEmail("user3", "user3@users.com");
        Assertions.assertTrue(mayBeUser.isPresent());
        User user = mayBeUser.get();
        Assertions.assertEquals("user3", user.getName());
        Assertions.assertEquals("user3@users.com", user.getEmail());
    }

    @Test
    void shouldReturnEmptyWhenUserNotPresentForGivenNameAndEmail() {
        Optional<User> mayBeUser = userService.getUserByNameAndEmail("user3", "user3@users.com");
        Assertions.assertFalse(mayBeUser.isPresent());
    }
}
