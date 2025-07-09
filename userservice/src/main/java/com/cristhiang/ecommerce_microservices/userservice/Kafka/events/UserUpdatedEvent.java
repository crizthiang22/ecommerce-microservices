package com.cristhiang.ecommerce_microservices.userservice.Kafka.events;

import java.time.LocalDateTime;

public class UserUpdatedEvent {
    private Long userId;
    private String email;
    private String username;
    private LocalDateTime updatedAt;

    public UserUpdatedEvent() {}

    public UserUpdatedEvent(Long userId, String email, String username, LocalDateTime updatedAt) {
        this.userId = userId;
        this.email = email;
        this.username = username;
        this.updatedAt = updatedAt;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
} 