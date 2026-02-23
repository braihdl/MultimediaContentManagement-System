package model;

import java.time.LocalDateTime;

public class BackupJob {

    private int id;
    private int createdBy;
    private String zipPath;
    private LocalDateTime createdAt;

    public BackupJob(int id, int createdBy, String zipPath, LocalDateTime createdAt) {
        this.id = id;
        this.createdBy = createdBy;
        this.zipPath = zipPath;
        this.createdAt = createdAt;
    }

    public int getId() { return id; }
    public int getCreatedBy() { return createdBy; }
    public String getZipPath() { return zipPath; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    @Override
    public String toString() {
        return "BackupJob{" +
                "id=" + id +
                ", createdBy=" + createdBy +
                ", zipPath='" + zipPath + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}