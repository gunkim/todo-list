package dev.gunlog.domain.todo.usecase;

import dev.gunlog.application.spring.web.dto.TodoRequestDto;

public interface CreateTodoUseCase {
    void createTodo(String memberId, TodoRequestDto dto);
}
