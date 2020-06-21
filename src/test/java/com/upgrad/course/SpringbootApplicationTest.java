package com.upgrad.course;

import com.upgrad.course.entity.Comment;
import com.upgrad.course.entity.Like;
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

import java.util.Arrays;
import java.util.Collections;
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
    void shouldReturnPostByPostId() {
        Post postToSave = new Post("userId1", "Moved to Mumbai");
        postToSave.setLikes(Collections.singletonList(new Like("user2")));
        postToSave.setComments(Collections.singletonList(new Comment("user3")));
        Post savedPost = postRepository.save(postToSave);

        final String baseUrl = "http://localhost:" + randomServerPort + "/api/v1";
        ResponseEntity<Post> response = testRestTemplate.getForEntity(baseUrl + "/posts/" + savedPost.getId(), Post.class);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Post post = response.getBody();
        Assertions.assertNotNull(post);
        Assertions.assertEquals(savedPost.getId(), post.getId());
        Assertions.assertEquals(savedPost.getUserId(), post.getUserId());
        Assertions.assertEquals(savedPost.getMessage(), post.getMessage());
        Assertions.assertEquals(1, post.getLikes().size());
        Assertions.assertEquals(1, post.getComments().size());
    }

    @Test
    void shouldReturn404ResponseWhenPostNotFoundByPostId() {
        final String baseUrl = "http://localhost:" + randomServerPort + "/api/v1";
        ResponseEntity<Post> response = testRestTemplate.getForEntity(baseUrl + "/posts/" + 123, Post.class);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void shouldReturnListOfLikesWhenPresentForPost() {
        Post postToSave = new Post("userId1", "Moved to Mumbai");
        postToSave.setLikes(Collections.singletonList(new Like("user2")));
        Post savedPost = postRepository.save(postToSave);

        final String baseUrl = "http://localhost:" + randomServerPort + "/api/v1";
        ResponseEntity<Like[]> response = testRestTemplate.getForEntity(baseUrl + "/posts/" + savedPost.getId() + "/likes", Like[].class);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        List<Like> likes = Arrays.asList(response.getBody());
        Assertions.assertEquals(1, likes.size());
        Like firstLike = likes.get(0);
        Assertions.assertEquals(firstLike.getLikedByUserId(), "user2");
    }

    @Test
    void shouldReturnEmptyListOfLikesWhenPostIsNotPresent() {
        final String baseUrl = "http://localhost:" + randomServerPort + "/api/v1";
        ResponseEntity<Like[]> response = testRestTemplate.getForEntity(baseUrl + "/posts/123/likes", Like[].class);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        List<Like> likes = Arrays.asList(response.getBody());
        Assertions.assertEquals(0, likes.size());
    }
}
