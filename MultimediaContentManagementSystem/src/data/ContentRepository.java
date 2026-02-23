package data;

import model.Content;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ContentRepository {

    private final List<Content> contents = new ArrayList<>();
    private int nextId = 1;

    public Content add(Content content) {
        content.setId(nextId++);
        contents.add(content);
        return content;
    }

    public List<Content> getAll() {
        return Collections.unmodifiableList(contents);
    }

    public Content findById(int id) {
        for (Content c : contents) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    public void remove(Content content) {
        contents.remove(content);
    }
}