package com.upgrad.course.controller;

import com.upgrad.course.entity.Comment;
import com.upgrad.course.entity.Like;
import com.upgrad.course.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // TODO: Define API Endpoint for getting comments of a post with limit
    //  Use RequestMapping annotation
    //  Define "postId" as a path parameter (Note: use plural value for the post resource in the path)
    //  Define "limit" as a query parameter and use appropriate annotation to receive it as a method argument
    @RequestMapping("/posts/{postId}/comments")
    public ResponseEntity<List<Comment>> getCommentsForPostWithLimit(@PathVariable String postId, @RequestParam("limit") int limit) {

        // TODO: Call service method to get the comments of a post using postId and limit
        //  Return 200 OK response with the body as List<Comment> got from the service
        List<Comment> comments = postService.getCommentsForPostWithLimit(Long.parseLong(postId), limit);
        return ResponseEntity.ok(comments);
    }
}
