package com.upgrad.course.entity;

import javax.persistence.*;
import java.time.Instant;
import java.util.Collections;
import java.util.List;

@Entity
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String userId;

    private String message;

    private Instant createdAt;

    @ElementCollection
    private List<LikeEntity> likeEntities;

    @ElementCollection
    private List<CommentEntity> commentEntities;

    public PostEntity() {
    }

    public PostEntity(String userId, String message) {
        this.userId = userId;
        this.message = message;
        this.createdAt = Instant.now();
        this.likeEntities = Collections.emptyList();
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

    public List<LikeEntity> getLikeEntities() {
        return likeEntities;
    }

    public void setLikeEntities(List<LikeEntity> likeEntities) {
        this.likeEntities = likeEntities;
    }

    public List<CommentEntity> getCommentEntities() {
        return commentEntities;
    }

    public void setCommentEntities(List<CommentEntity> commentEntities) {
        this.commentEntities = commentEntities;
    }
}
