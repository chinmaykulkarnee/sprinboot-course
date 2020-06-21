package com.upgrad.course;

import com.upgrad.course.entity.Post;
import com.upgrad.course.repository.PostRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringbootApplicationTest {

    @LocalServerPort
    int randomServerPort;

    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    private PostRepository postRepository;

    @BeforeEach
    void clean() {
        postRepository.deleteAll();
    }

    @Test
    public void shouldReturn201WithPostIdWhenPostIsCreatedSuccessfully() {
        Post postToSave = new Post("userId1", "Moved to Mumbai");

        final String baseUrl = "http://localhost:" + randomServerPort + "/api/v1";
        ResponseEntity<Long> response = testRestTemplate.postForEntity(baseUrl + "/posts", postToSave, Long.class);

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void shouldReturnFailureIfPostWasAlreadyPresent() {
        Post postToSave = new Post("userId1", "Moved to Mumbai");

        final String baseUrl = "http://localhost:" + randomServerPort + "/api/v1";
        ResponseEntity<Long> response1 = testRestTemplate.postForEntity(baseUrl + "/posts", postToSave, Long.class);
        Assertions.assertEquals(HttpStatus.CREATED, response1.getStatusCode());

        ResponseEntity<Long> response2 = testRestTemplate.postForEntity(baseUrl + "/posts", postToSave, Long.class);
        Assertions.assertEquals(HttpStatus.CONFLICT, response2.getStatusCode());
    }
}
