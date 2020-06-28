package com.upgrad.course.service;

import com.upgrad.course.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public boolean deletePost(Long postId) {
        if (postRepository.existsById(postId)) {

            // TODO: Use repository method to delete the post by addressId when post already exists
            postRepository.deleteById(postId);
            return true;
        }
        else {
            return false;
        }
    }
}
