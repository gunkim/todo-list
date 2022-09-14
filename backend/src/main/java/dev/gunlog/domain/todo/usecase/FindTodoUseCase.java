package dev.gunlog.domain.todo.usecase;

import dev.gunlog.domain.todo.Todo;
import java.util.List;

public interface FindTodoUseCase {
    List<Todo> find(String memberId);
}
