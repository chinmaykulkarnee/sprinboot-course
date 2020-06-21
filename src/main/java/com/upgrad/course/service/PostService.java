package com.upgrad.course.service;

import com.upgrad.course.entity.Comment;
import com.upgrad.course.entity.Like;
import com.upgrad.course.entity.Post;
import com.upgrad.course.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    private PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Comment> getCommentsForPostWithLimit(Long postId, int limit) {
        List<Comment> comments;

        // TODO: Use repository to get comments of a post by id (the post repository method returns an Optional<Post> object)
        //  Assign the comments to comments variable defined above
        //  Assign list of comments got from the repository when post is present
        //  Assign empty list when post is absent
        comments = this.postRepository.findById(postId)
                .map(Post::getComments)
                .orElse(Collections.emptyList());

        return comments.stream().limit(limit).collect(Collectors.toList());
    }
}
