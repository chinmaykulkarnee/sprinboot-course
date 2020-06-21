package com.upgrad.course.service;

import com.upgrad.course.entity.Like;
import com.upgrad.course.entity.Post;
import com.upgrad.course.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    // TODO: Use repository to get post by id
    public Optional<Post> getPostById(Long postId) {
        return this.postRepository.findById(postId);
    }

    // TODO: Use repository to get likes of a post by id (the post repository method returns an Optional<Post> object)
    //  Return list of likes when post is present
    //  Return empty list when post is absent
    public List<Like> getLikesForPost(Long postId) {
        return this.postRepository.findById(postId)
                .map(Post::getLikes)
                .orElse(Collections.emptyList());
    }
}
