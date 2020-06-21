package com.upgrad.course.entity;

import javax.persistence.Embeddable;
import java.time.Instant;

@Embeddable
public class Like {
    private String likedByUserId;

    private Instant likedAt;

    public Like() {
    }

    public Like(String likedByUserId) {
        this.likedByUserId = likedByUserId;
        this.likedAt = Instant.now();
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
}
