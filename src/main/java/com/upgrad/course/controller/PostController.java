package com.upgrad.course.controller;

import com.upgrad.course.dto.entity.CommentDto;
import com.upgrad.course.dto.entity.PostDto;
import com.upgrad.course.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    @RequestMapping(value = "/posts/{postId}", method = RequestMethod.GET)
    public ResponseEntity<PostDto> getPostDetails(@PathVariable Long postId) {

        Optional<PostDto> mayBePost = postService.getPost(postId);
        // TODO: Call service method to get the details of a post using postId
        //  Return 404 NOT_FOUND response when service empty Optional response
        //  Otherwise return 200 OK response with PostDto as body
        return mayBePost
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // TODO: Define API Endpoint for getting comments of a post with limit
    //  Define "postId" as a path parameter
    //  Define "limit" as a query parameter and use appropriate annotation to receive it as a method argument
    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<List<CommentDto>> getCommentsForPostWithLimit(@PathVariable Long postId, @RequestParam("limit") int limit) {

        // TODO: Call service method to get the comments of a post using postId and limit
        //  Return 200 OK response with the body as List<Comment> got from the service
        List<CommentDto> commentEntities = postService.getCommentsForPostWithLimit(postId, limit);
        return ResponseEntity.ok(commentEntities);
    }
}
