package model;

import java.time.LocalDateTime;

public class Notification {

    private int id;
    private int userId;
    private String message;
    private LocalDateTime createdAt;

    public Notification(int id, int userId, String message, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.message = message;
        this.createdAt = createdAt;
    }

    public int getId() { return id; }
    public int getUserId() { return userId; }
    public String getMessage() { return message; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    @Override
    public String toString() {
        return "[" + createdAt + "] " + message;
    }
}