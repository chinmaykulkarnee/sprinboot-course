package com.upgrad.course;

import com.upgrad.course.entity.Comment;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    void shouldReturnThreeCommentsWhenLimitIsThreeEvenWhenFiveCommentsExistForPost() {
        Post postToSave = new Post("userId1", "Moved to Mumbai");
        ArrayList<Comment> comments = new ArrayList<>();
        comments.add(new Comment("user1"));
        comments.add(new Comment("user2"));
        comments.add(new Comment("user3"));
        comments.add(new Comment("user4"));
        comments.add(new Comment("user5"));
        postToSave.setComments(comments);
        Post savedPost = postRepository.save(postToSave);

        final String baseUrl = "http://localhost:" + randomServerPort + "/api/v1";
        ResponseEntity<Comment[]> response = testRestTemplate.getForEntity(baseUrl + "/posts/" + savedPost.getId() + "/comments?limit=3", Comment[].class);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        List<Comment> commentsResponse = Arrays.asList(response.getBody());
        Assertions.assertEquals(3, commentsResponse.size());
    }

    @Test
    void shouldReturnTwoCommentsWhenLimitIsThreeButOnlyTwoCommentsExistForPost() {
        Post postToSave = new Post("userId1", "Moved to Mumbai");
        ArrayList<Comment> comments = new ArrayList<>();
        comments.add(new Comment("user1"));
        comments.add(new Comment("user2"));
        postToSave.setComments(comments);
        Post savedPost = postRepository.save(postToSave);

        final String baseUrl = "http://localhost:" + randomServerPort + "/api/v1";
        ResponseEntity<Comment[]> response = testRestTemplate.getForEntity(baseUrl + "/posts/" + savedPost.getId() + "/comments?limit=3", Comment[].class);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        List<Comment> commentsResponse = Arrays.asList(response.getBody());
        Assertions.assertEquals(2, commentsResponse.size());
    }

    @Test
    void shouldReturnEmptyListOfCommentsWhenPostIsNotPresent() {
        final String baseUrl = "http://localhost:" + randomServerPort + "/api/v1";
        ResponseEntity<Comment[]> response = testRestTemplate.getForEntity(baseUrl + "/posts/123/comments?limit=5", Comment[].class);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        List<Comment> comments = Arrays.asList(response.getBody());
        Assertions.assertEquals(0, comments.size());
    }
}
