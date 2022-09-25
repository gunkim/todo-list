package dev.gunlog.usecase.todo;

import dev.gunlog.domain.todo.Todos;

public interface FindTodoUseCase {

    Todos find(String memberId);
}
