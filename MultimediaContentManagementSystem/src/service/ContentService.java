package service;

import data.ContentRepository;
import data.NotificationRepository;
import model.Content;
import model.User;

import java.time.LocalDateTime;
import java.util.List;

public class ContentService {

    private final ContentRepository contentRepository;
    private final NotificationRepository notificationRepository;

    public ContentService(ContentRepository contentRepository,
                          NotificationRepository notificationRepository) {
        this.contentRepository = contentRepository;
        this.notificationRepository = notificationRepository;
    }

    public Content upload(Content content, User uploader) {

        if (!uploader.canUpload()) {
            throw new IllegalArgumentException("User does not have upload permission.");
        }

        content.setUploaderId(uploader.getId());
        content.setUploadedAt(LocalDateTime.now());

        Content saved = contentRepository.add(content);

        notificationRepository.add(
                uploader.getId(),
                "Content uploaded successfully: " + content.getTitle()
        );

        return saved;
    }

    public List<Content> getAllContent() {
        return contentRepository.getAll();
    }

    public void delete(int contentId, User user) {

        if (!user.canDelete()) {
            throw new IllegalArgumentException("User does not have delete permission.");
        }

        Content content = contentRepository.findById(contentId);

        if (content == null) {
            throw new IllegalArgumentException("Content not found.");
        }

        contentRepository.remove(content);

        notificationRepository.add(
                user.getId(),
                "Content deleted: " + content.getTitle()
        );
    }
}