package data;

import model.Notification;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class NotificationRepository {

    private final List<Notification> notifications = new ArrayList<>();
    private int nextId = 1;

    public void add(int userId, String message) {
        notifications.add(
                new Notification(nextId++, userId, message, LocalDateTime.now())
        );
    }

    public List<Notification> forUser(int userId) {
        List<Notification> result = new ArrayList<>();
        for (Notification n : notifications) {
            if (n.getUserId() == userId) {
                result.add(n);
            }
        }
        return result;
    }
}