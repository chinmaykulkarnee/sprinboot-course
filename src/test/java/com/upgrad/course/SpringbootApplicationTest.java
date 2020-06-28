package com.upgrad.course;

import com.upgrad.course.dto.entity.CommentDto;
import com.upgrad.course.dto.entity.PostDto;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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
    void shouldReturn200WithPostDtoWhenPostIsPresent() {
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
        ResponseEntity<PostDto> response = testRestTemplate.getForEntity(baseUrl + "/posts/" + savedPostEntity.getId(), PostDto.class);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        PostDto postDto = response.getBody();
        System.out.println(postDto);
        Assertions.assertNotNull(postDto);
        Assertions.assertEquals(savedPostEntity.getId(), postDto.getId());
        Assertions.assertEquals(savedPostEntity.getCommentEntities().size(), postDto.getComments().size());
        Assertions.assertEquals(savedPostEntity.getLikeEntities().size(), postDto.getLikes().size());
    }

    @Test
    void shouldReturn404WhenPostIsNotPresent() {
        final String baseUrl = "http://localhost:" + randomServerPort + "/api/v1";
        ResponseEntity<PostDto> response = testRestTemplate.getForEntity(baseUrl + "/posts/123", PostDto.class);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void shouldReturnThreeCommentsWhenLimitIsThreeEvenWhenFiveCommentsExistForPost() {
        PostEntity postEntityToSave = new PostEntity("userId1", "Moved to Mumbai");
        ArrayList<CommentEntity> commentEntities = new ArrayList<>();
        commentEntities.add(new CommentEntity(1L, "user1"));
        commentEntities.add(new CommentEntity(2L,"user2"));
        commentEntities.add(new CommentEntity(3L,"user3"));
        commentEntities.add(new CommentEntity(4L,"user4"));
        commentEntities.add(new CommentEntity(5L,"user5"));
        postEntityToSave.setCommentEntities(commentEntities);
        PostEntity savedPostEntity = postRepository.save(postEntityToSave);

        final String baseUrl = "http://localhost:" + randomServerPort + "/api/v1";
        ResponseEntity<CommentDto[]> response = testRestTemplate.getForEntity(baseUrl + "/posts/" + savedPostEntity.getId() + "/comments?limit=3", CommentDto[].class);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        List<CommentDto> commentsResponse = Arrays.asList(Objects.requireNonNull(response.getBody()));
        Assertions.assertEquals(3, commentsResponse.size());
    }

    @Test
    void shouldReturnTwoCommentsWhenLimitIsThreeButOnlyTwoCommentsExistForPost() {
        PostEntity postEntityToSave = new PostEntity("userId1", "Moved to Mumbai");
        ArrayList<CommentEntity> commentEntities = new ArrayList<>();
        commentEntities.add(new CommentEntity(1L,"user1"));
        commentEntities.add(new CommentEntity(2L,"user2"));
        postEntityToSave.setCommentEntities(commentEntities);
        PostEntity savedPostEntity = postRepository.save(postEntityToSave);

        final String baseUrl = "http://localhost:" + randomServerPort + "/api/v1";
        ResponseEntity<CommentDto[]> response = testRestTemplate.getForEntity(baseUrl + "/posts/" + savedPostEntity.getId() + "/comments?limit=3", CommentDto[].class);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        List<CommentDto> commentsResponse = Arrays.asList(Objects.requireNonNull(response.getBody()));
        Assertions.assertEquals(2, commentsResponse.size());
    }

    @Test
    void shouldReturnEmptyListOfCommentsWhenPostIsNotPresent() {
        final String baseUrl = "http://localhost:" + randomServerPort + "/api/v1";
        ResponseEntity<CommentDto[]> response = testRestTemplate.getForEntity(baseUrl + "/posts/123/comments?limit=5", CommentDto[].class);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        List<CommentDto> commentEntities = Arrays.asList(Objects.requireNonNull(response.getBody()));
        Assertions.assertEquals(0, commentEntities.size());
    }
}
