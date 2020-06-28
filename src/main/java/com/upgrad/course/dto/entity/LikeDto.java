package com.upgrad.course.dto.entity;

import com.upgrad.course.entity.LikeEntity;

import java.time.Instant;

public class LikeDto {
    private String likedByUserId;

    private Instant likedAt;

    public LikeDto() {
    }

    public LikeDto(String likedByUserId, Instant likedAt) {
        this.likedByUserId = likedByUserId;
        this.likedAt = likedAt;
    }

    public String getLikedByUserId() {
        return likedByUserId;
    }

    public void setLikedByUserId(String likedByUserId) {
        this.likedByUserId = likedByUserId;
    }

    public Instant getLikedAt() {
        return likedAt;
    }

    public void setLikedAt(Instant likedAt) {
        this.likedAt = likedAt;
    }

    public static LikeDto buildFrom(LikeEntity entity) {
        return new LikeDto(entity.getLikedByUserId(), entity.getLikedAt());
    }
}
