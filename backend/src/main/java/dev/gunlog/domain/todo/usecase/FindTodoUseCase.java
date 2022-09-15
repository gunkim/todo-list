package dev.gunlog.domain.todo.usecase;

import dev.gunlog.domain.todo.Todos;

public interface FindTodoUseCase {

    Todos find(String memberId);
}
