package controller;

import data.BackupRepository;
import data.ContentRepository;
import data.NotificationRepository;
import data.UserRepository;
import model.Content;
import model.MediaType;
import model.Notification;
import model.User;
import service.AdminService;
import service.AuthService;
import service.BackupService;
import service.ContentService;
import service.ReportService;
import view.ConsoleView;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;

public class AppController {

    private final ConsoleView view;

    private final UserRepository userRepository = new UserRepository();
    private final ContentRepository contentRepository = new ContentRepository();
    private final NotificationRepository notificationRepository = new NotificationRepository();
    private final BackupRepository backupRepository = new BackupRepository();

    private final AuthService authService = new AuthService(userRepository);
    private final AdminService adminService = new AdminService(userRepository);
    private final ContentService contentService = new ContentService(contentRepository, notificationRepository);
    private final BackupService backupService = new BackupService(backupRepository);
    private final ReportService reportService = new ReportService(contentRepository);

    private User currentUser;

    public AppController(ConsoleView view) {
        this.view = view;
    }

    public void start() {
        view.printTitle("MCMS - Java Application (MVC / No DB)");
        login();

        while (true) {
            int choice = view.mainMenu();
            try {
                switch (choice) {
                    case 1 -> uploadContent();
                    case 2 -> viewAllContent();
                    case 3 -> deleteContent();
                    case 4 -> viewNotifications();
                    case 5 -> viewReports();
                    case 6 -> createBackup();
                    case 0 -> {
                        view.showMessage("Goodbye!");
                        return;
                    }
                    default -> view.showMessage("Invalid choice.");
                }
            } catch (Exception e) {
                view.showMessage("ERROR: " + e.getMessage());
            }
        }
    }

    private void login() {
        while (true) {
            view.printTitle("Login");
            String username = view.prompt("Username (admin/creator/user)");
            String password = view.prompt("Password (1234)");

            User user = authService.login(username, password);
            if (user != null) {
                currentUser = user;
                view.showMessage("Login successful. Welcome " + currentUser.getUsername());
                return;
            }
            view.showMessage("Invalid credentials, try again.");
        }
    }

    private void uploadContent() throws Exception {
        view.printTitle("Upload Content");

        Content c = new Content();
        c.setTitle(view.prompt("Title"));
        c.setDescription(view.prompt("Description (optional)"));
        c.setCategory(view.prompt("Category (optional)"));

        String typeStr = view.prompt("Media Type (IMAGE/VIDEO/AUDIO)").toUpperCase();
        c.setMediaType(MediaType.valueOf(typeStr));

        String filePath = view.prompt("Full file path");
        Path source = Path.of(filePath);

        if (Files.notExists(source)) {
            throw new IllegalArgumentException("File not found.");
        }

        // Save file to uploads folder
        Path uploadsDir = Path.of("uploads");
        if (Files.notExists(uploadsDir)) {
            Files.createDirectories(uploadsDir);
        }

        String originalName = source.getFileName().toString();
        String ext = getExt(originalName);
        String storedName = "file_" + System.currentTimeMillis() + (ext.isEmpty() ? "" : "." + ext);
        Path target = uploadsDir.resolve(storedName);

        Files.copy(source, target);

        c.setOriginalFilename(originalName);
        c.setStoredFilename(storedName);
        c.setStoredPath(target.toAbsolutePath().toString());
        c.setFileSize(Files.size(target));
        c.setUploadedAt(LocalDateTime.now());

        contentService.upload(c, currentUser);

        view.showMessage("Uploaded successfully to: " + target.toAbsolutePath());
    }

    private void viewAllContent() {
        view.printTitle("All Content");
        List<Content> list = contentService.getAllContent();
        view.showContentList(list);
    }

    private void deleteContent() {
        view.printTitle("Delete Content");
        int id = Integer.parseInt(view.prompt("Content ID"));
        contentService.delete(id, currentUser);
        view.showMessage("Deleted successfully.");
    }

    private void viewNotifications() {
        view.printTitle("Notifications");
        List<Notification> list = notificationRepository.forUser(currentUser.getId());
        if (list.isEmpty()) {
            view.showMessage("No notifications.");
            return;
        }
        for (Notification n : list) {
            System.out.println(n);
        }
    }

    private void viewReports() {
        view.printTitle("Reports");
        view.showReport(
                reportService.totalFiles(),
                reportService.totalSizeBytes(),
                reportService.countByType()
        );
    }

    private void createBackup() throws Exception {
        view.printTitle("Backup");
        String zipPath = backupService.backupUploads(currentUser);
        view.showMessage("Backup created: " + zipPath);
    }

    private String getExt(String name) {
        int dot = name.lastIndexOf('.');
        if (dot < 0 || dot == name.length() - 1) return "";
        return name.substring(dot + 1).toLowerCase();
    }
}