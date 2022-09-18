package dev.gunlog.domain;

import dev.gunlog.domain.member.Member;
import dev.gunlog.domain.todo.Todo;
import dev.gunlog.domain.todo.TodoRepository;
import dev.gunlog.domain.todo.Todos;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FakeTodoRepository implements TodoRepository {

    private final Map<Long, Todo> content = new HashMap<>();

    @Override
    public Todos findAll() {
        return new Todos(content.values().stream().toList());
    }

    @Override
    public Optional<Todo> findById(Long id) {
        return Optional.ofNullable(content.get(id));
    }

    @Override
    public Todo save(Todo todo) {
        return Optional.ofNullable(todo)
            .map(t -> content.put(generateId(t), t))
            .orElse(todo);
    }

    @Override
    public void delete(Todo todo) {
        content.remove(todo.id());
    }

    @Override
    public Todos findAllByMember(Member member) {
        return new Todos(content.values().stream().filter(todo -> todo.member().equals(member)).toList());
    }

    @Override
    public Optional<Todo> findByIdAndMember(Long id, Member member) {
        return content.values().stream().filter(todo -> todo.id() == id).filter(todo -> todo.member().equals(member))
            .findFirst();
    }

    private Long generateId(Todo todo) {
        if (todo.id() != null) {
            return todo.id();
        }
        return (long) content.size();
    }
}
