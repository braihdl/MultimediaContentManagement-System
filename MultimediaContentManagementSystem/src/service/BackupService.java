package service;

import data.BackupRepository;
import model.User;
import model.UserRole;

import java.io.FileOutputStream;
import java.nio.file.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class BackupService {

    private final BackupRepository backupRepository;

    public BackupService(BackupRepository backupRepository) {
        this.backupRepository = backupRepository;
    }

    public String backupUploads(User admin) throws Exception {

        if (admin == null || admin.getRole() != UserRole.ADMIN) {
            throw new IllegalArgumentException("Only ADMIN can create backups.");
        }

        Path uploadsDir = Paths.get("uploads");
        if (Files.notExists(uploadsDir)) {
            throw new IllegalArgumentException("Uploads folder does not exist yet.");
        }

        Path backupsDir = Paths.get("backups");
        if (Files.notExists(backupsDir)) {
            Files.createDirectories(backupsDir);
        }

        String zipName = "uploads_backup_" + System.currentTimeMillis() + ".zip";
        Path zipPath = backupsDir.resolve(zipName);

        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipPath.toFile()))) {
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(uploadsDir)) {
                for (Path p : stream) {
                    if (!Files.isRegularFile(p)) continue;

                    zos.putNextEntry(new ZipEntry(p.getFileName().toString()));
                    Files.copy(p, zos);
                    zos.closeEntry();
                }
            }
        }

        backupRepository.add(admin.getId(), zipPath.toAbsolutePath().toString());
        return zipPath.toAbsolutePath().toString();
    }
}