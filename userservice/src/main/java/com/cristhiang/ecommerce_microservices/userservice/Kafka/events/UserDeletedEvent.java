package com.cristhiang.ecommerce_microservices.userservice.Kafka.events;

import java.time.LocalDateTime;

public class UserDeletedEvent {
    private Long userId;
    private String email;
    private LocalDateTime deletedAt;

    public UserDeletedEvent() {}

    public UserDeletedEvent(Long userId, String email, LocalDateTime deletedAt) {
        this.userId = userId;
        this.email = email;
        this.deletedAt = deletedAt;
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

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }
} 