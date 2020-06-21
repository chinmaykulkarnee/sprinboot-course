package com.upgrad.course.entity;

import javax.persistence.Embeddable;
import java.time.Instant;

@Embeddable
public class Comment {
    private String commentedByUserId;

    private Instant commentedAt;

    public Comment() {
    }

    public Comment(String commentedByUserId) {
        this.commentedByUserId = commentedByUserId;
        this.commentedAt = Instant.now();
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
