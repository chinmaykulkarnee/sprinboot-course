package com.upgrad.course.controller;

import com.upgrad.course.entity.Like;
import com.upgrad.course.entity.Post;
import com.upgrad.course.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // TODO: Define API Endpoint for getting post details by postId
    //  Use GetMapping annotation
    //  Define postId as a path parameter
    //  Note: use plural value for the post resource in the path
    @GetMapping("/posts/{postId}")
    // TODO: Use correct annotation and variable name to receiving the path variable value as a method argument
    public ResponseEntity<Post> getPost(@PathVariable String postId) {

        // TODO: Call service method to get the post using postId
        //  Return 200 OK response with the body as Post object if found
        //  Return 404 Not Found response with no body when post is not found
        Optional<Post> mayBePost = postService.getPostById(Long.parseLong(postId));
        return mayBePost
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/posts/{postId}/likes")
    public ResponseEntity<List<Like>> getLikesForPost(@PathVariable String postId) {

        // TODO: Call service method to get the likes of a post using postId
        //  Return 200 OK response with the body as List<Like> got from the service
        List<Like> likes = postService.getLikesForPost(Long.parseLong(postId));
        return ResponseEntity.ok(likes);
    }
}
