package com.upgrad.course.dto.entity;

import com.upgrad.course.entity.Status;
import com.upgrad.course.entity.TaskEntity;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

public class TaskDto {
    private Long id;

    private String userId;

    private String message;

    private Instant createdAt;

    private Status status;

    public TaskDto() {
    }

    public TaskDto(Long id, String userId, String message, Instant createdAt, Status status) {
        this.id = id;
        this.userId = userId;
        this.message = message;
        this.createdAt = createdAt;
        this.status = status;
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

    public static TaskDto buildFrom(TaskEntity entity) {
        return new TaskDto(entity.getId(), entity.getUserId(), entity.getMessage(), entity.getCreatedAt(), entity.getStatus());
    }

    public TaskEntity convertToEntity() {
        return new TaskEntity(userId, message);
    }

    @Override
    public String toString() {
        return "TaskDto{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", message='" + message + '\'' +
                ", createdAt=" + createdAt +
                ", status=" + status +
                '}';
    }
}
