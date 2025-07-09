package com.cristhiang.ecommerce_microservices.userservice.Kafka.events;

import java.time.LocalDateTime;

public class UserCreatedEvent {
    private Long userId;
    private String email;
    private String username;
    private LocalDateTime createdAt;

    public UserCreatedEvent() {}

    public UserCreatedEvent(Long userId, String email, String username, LocalDateTime createdAt) {
        this.userId = userId;
        this.email = email;
        this.username = username;
        this.createdAt = createdAt;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
} 