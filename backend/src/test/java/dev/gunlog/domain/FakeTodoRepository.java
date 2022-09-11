package dev.gunlog.domain;

import dev.gunlog.domain.member.Member;
import dev.gunlog.domain.todo.Todo;
import dev.gunlog.domain.todo.TodoRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class FakeTodoRepository implements TodoRepository {

    private final Map<Long, Todo> content = new HashMap<>();

    @Override
    public List<Todo> findAll() {
        return content.values().stream().toList();
    }

    @Override
    public Optional<Todo> findById(Long id) {
        return Optional.ofNullable(content.get(id));
    }

    @Override
    public Todo save(Todo todo) {
        return content.put(generateId(todo), todo);
    }

    @Override
    public Todo delete(Todo todo) {
        return content.remove(todo);
    }

    @Override
    public List<Todo> findAllByMember(Member member) {
        return content.values().stream()
            .filter(todo -> todo.member().equals(member))
            .toList();
    }

    @Override
    public Optional<Todo> findByIdAndMember(Long id, Member member) {
        return content.values().stream()
            .filter(todo -> todo.id() == id)
            .filter(todo -> todo.member().equals(member))
            .findFirst();
    }

    private Long generateId(Todo todo) {
        if (todo.id() != null) {
            return todo.id();
        }
        return (long) content.size();
    }
}
