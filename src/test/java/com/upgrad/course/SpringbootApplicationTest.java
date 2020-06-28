package com.upgrad.course;

import com.upgrad.course.entity.CommentEntity;
import com.upgrad.course.entity.LikeEntity;
import com.upgrad.course.entity.PostEntity;
import com.upgrad.course.repository.PostRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

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
    void shouldReturn200WhenPostIsDeletedSuccessfully() {
        PostEntity postEntityToSave = new PostEntity("userId1", "Moved to Mumbai");
        ArrayList<LikeEntity> likeEntities = new ArrayList<>();
        likeEntities.add(new LikeEntity("user5"));
        likeEntities.add(new LikeEntity("user6"));
        postEntityToSave.setLikeEntities(likeEntities);
        ArrayList<CommentEntity> commentEntities = new ArrayList<>();
        commentEntities.add(new CommentEntity(1L, "user1"));
        commentEntities.add(new CommentEntity(2L,"user2"));
        postEntityToSave.setCommentEntities(commentEntities);
        PostEntity savedPostEntity = postRepository.save(postEntityToSave);

        final String baseUrl = "http://localhost:" + randomServerPort + "/api/v1";
        ResponseEntity<Void> response = testRestTemplate.exchange(baseUrl + "/posts/{postId}", HttpMethod.DELETE, null, Void.class, savedPostEntity.getId());

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertFalse(postRepository.existsById(savedPostEntity.getId()));
    }

    @Test
    void shouldReturn404WhenPostIsNotPresent() {
        final String baseUrl = "http://localhost:" + randomServerPort + "/api/v1";
        ResponseEntity<Void> response = testRestTemplate.exchange(baseUrl + "/posts/{postId}", HttpMethod.DELETE, null, Void.class, 123L);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
