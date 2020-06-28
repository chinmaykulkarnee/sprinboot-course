package com.upgrad.course.service;

import com.upgrad.course.dto.entity.CommentDto;
import com.upgrad.course.dto.entity.PostDto;

import java.util.List;
import java.util.Optional;

public interface PostService {
    List<CommentDto> getCommentsForPostWithLimit(Long postId, int limit);

    Optional<PostDto> getPost(Long postId);
}
