package data;

import model.BackupJob;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BackupRepository {

    private final List<BackupJob> backups = new ArrayList<>();
    private int nextId = 1;

    public void add(int adminId, String zipPath) {
        backups.add(
                new BackupJob(nextId++, adminId, zipPath, LocalDateTime.now())
        );
    }

    public List<BackupJob> getAll() {
        return backups;
    }
}