package model;

import java.time.LocalDateTime;

public class Content {

    private int id;
    private String title;
    private String description;
    private MediaType mediaType;
    private String category;

    private String originalFilename;
    private String storedFilename;
    private String storedPath;
    private long fileSize;

    private int uploaderId;
    private LocalDateTime uploadedAt;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public MediaType getMediaType() { return mediaType; }
    public void setMediaType(MediaType mediaType) { this.mediaType = mediaType; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getOriginalFilename() { return originalFilename; }
    public void setOriginalFilename(String originalFilename) { this.originalFilename = originalFilename; }

    public String getStoredFilename() { return storedFilename; }
    public void setStoredFilename(String storedFilename) { this.storedFilename = storedFilename; }

    public String getStoredPath() { return storedPath; }
    public void setStoredPath(String storedPath) { this.storedPath = storedPath; }

    public long getFileSize() { return fileSize; }
    public void setFileSize(long fileSize) { this.fileSize = fileSize; }

    public int getUploaderId() { return uploaderId; }
    public void setUploaderId(int uploaderId) { this.uploaderId = uploaderId; }

    public LocalDateTime getUploadedAt() { return uploadedAt; }
    public void setUploadedAt(LocalDateTime uploadedAt) { this.uploadedAt = uploadedAt; }

    @Override
    public String toString() {
        return "Content{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", mediaType=" + mediaType +
                ", category='" + category + '\'' +
                ", fileSize=" + fileSize +
                ", uploaderId=" + uploaderId +
                ", uploadedAt=" + uploadedAt +
                '}';
    }
}