package service;

import data.ContentRepository;
import model.Content;
import model.MediaType;

import java.util.EnumMap;
import java.util.Map;

public class ReportService {

    private final ContentRepository contentRepository;

    public ReportService(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    public int totalFiles() {
        return contentRepository.getAll().size();
    }

    public long totalSizeBytes() {
        long sum = 0;
        for (Content c : contentRepository.getAll()) {
            sum += c.getFileSize();
        }
        return sum;
    }

    public Map<MediaType, Integer> countByType() {
        Map<MediaType, Integer> map = new EnumMap<>(MediaType.class);
        for (MediaType t : MediaType.values()) {
            map.put(t, 0);
        }
        for (Content c : contentRepository.getAll()) {
            map.put(c.getMediaType(), map.get(c.getMediaType()) + 1);
        }
        return map;
    }
}