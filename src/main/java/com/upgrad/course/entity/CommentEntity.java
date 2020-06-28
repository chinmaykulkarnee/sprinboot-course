package com.upgrad.course.entity;

import javax.persistence.Embeddable;
import java.time.Instant;

@Embeddable
public class CommentEntity {
    private Long id;

    private String commentedByUserId;

    private Instant commentedAt;

    public CommentEntity() {
    }

    public CommentEntity(Long id, String commentedByUserId) {
        this.id = id;
        this.commentedByUserId = commentedByUserId;
        this.commentedAt = Instant.now();
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
}
