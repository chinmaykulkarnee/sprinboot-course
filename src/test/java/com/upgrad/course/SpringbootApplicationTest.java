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
}
