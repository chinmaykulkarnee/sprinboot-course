package com.upgrad.course.dto.entity;

import com.upgrad.course.entity.PostEntity;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

public class PostDto {
    private Long id;

    private String userId;

    private String message;

    private Instant createdAt;

    private List<LikeDto> likes;

    private List<CommentDto> comments;

    public PostDto() {
    }

    public PostDto(Long id, String userId, String message, Instant createdAt, List<LikeDto> likes, List<CommentDto> comments) {
        this.id = id;
        this.userId = userId;
        this.message = message;
        this.createdAt = createdAt;
        this.likes = likes;
        this.comments = comments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String name) {
        this.userId = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public List<LikeDto> getLikes() {
        return likes;
    }

    public void setLikes(List<LikeDto> likes) {
        this.likes = likes;
    }

    public List<CommentDto> getComments() {
        return comments;
    }

    public void setComments(List<CommentDto> comments) {
        this.comments = comments;
    }

    public static PostDto buildFrom(PostEntity entity) {
        List<LikeDto> likes = entity.getLikeEntities().stream()
                .map(LikeDto::buildFrom)
                .collect(Collectors.toList());

        List<CommentDto> comments = entity.getCommentEntities().stream()
                .map(CommentDto::buildFrom)
                .collect(Collectors.toList());

        return new PostDto(entity.getId(), entity.getUserId(), entity.getMessage(), entity.getCreatedAt(), likes, comments);
    }

    @Override
    public String toString() {
        return "PostDto{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", message='" + message + '\'' +
                ", createdAt=" + createdAt +
                ", likes=" + likes +
                ", comments=" + comments +
                '}';
    }
}
