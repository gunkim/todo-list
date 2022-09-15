package dev.gunlog.domain.todo.usecase;

import dev.gunlog.application.spring.web.dto.TodoRequestDto;

public interface CreateTodoUseCase {
    Long create(String memberId, TodoRequestDto dto);
}
