package com.upgrad.course.controller;

import com.upgrad.course.entity.Post;
import com.upgrad.course.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // TODO: Define API Endpoint for adding a new post
    //  Use PostMapping annotation
    //  Use appropriate annotation to receive the request body as a method argument
    @PostMapping("/posts")
    public ResponseEntity<Long> getCommentsForPostWithLimit(@RequestBody Post post) {

        // TODO: Call service method to add a new post
        //  Return 201 CREATED response when service returns true as status
        //  Return 409 CONFLICT response when service returns false as status
        boolean status = postService.addPost(post);
        if (status) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
