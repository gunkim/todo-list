package dev.gunlog.domain.todo;

import java.util.List;

public record Todos(List<Todo> content) {

    public int size() {
        return content.size();
    }
}
