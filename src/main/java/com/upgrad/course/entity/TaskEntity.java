package com.upgrad.course.entity;

import javax.persistence.*;
import java.time.Instant;
import java.util.Collections;
import java.util.List;

@Entity
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String userId;

    @Column(unique = true)
    private String message;

    private Instant createdAt;

    private Status status;

    public TaskEntity() {
    }

    public TaskEntity(String userId, String message) {
        this.userId = userId;
        this.message = message;
        this.createdAt = Instant.now();
        this.status = Status.PENDING;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
