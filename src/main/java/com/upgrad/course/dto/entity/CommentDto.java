package com.upgrad.course.dto.entity;

import com.upgrad.course.entity.CommentEntity;

import java.time.Instant;

public class CommentDto {
    private Long id;

    private String commentedByUserId;

    private Instant commentedAt;

    public CommentDto(Long id, String commentedByUserId, Instant commentedAt) {
        this.id = id;
        this.commentedByUserId = commentedByUserId;
        this.commentedAt = commentedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCommentedByUserId() {
        return commentedByUserId;
    }

    public void setCommentedByUserId(String commentedByUserId) {
        this.commentedByUserId = commentedByUserId;
    }

    public Instant getCommentedAt() {
        return commentedAt;
    }

    public void setCommentedAt(Instant commentedAt) {
        this.commentedAt = commentedAt;
    }

    public static CommentDto buildFrom(CommentEntity entity) {
        return new CommentDto(entity.getId(), entity.getCommentedByUserId(), entity.getCommentedAt());
    }
}
