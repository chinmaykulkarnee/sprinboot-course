package com.upgrad.course.controller;

import com.upgrad.course.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // TODO: Define API Endpoint for getting comments of a post with limit
    //  Use @RequestMapping annotation with value and method arguments for defining the API
    //  Define "postId" as a path parameter
    @DeleteMapping(value = "/posts/{postId}")
    public ResponseEntity deletePost(@PathVariable Long postId) {

        // TODO: Call service method to delete the post using postId
        boolean result = postService.deletePost(postId);
        //  TODO: Return 404 NOT_FOUND response when service return false as result
        //   Otherwise return 200 OK response no body
        if (result) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
