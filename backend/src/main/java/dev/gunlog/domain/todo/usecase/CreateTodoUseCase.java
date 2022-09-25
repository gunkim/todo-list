package dev.gunlog.domain.todo.usecase;

public interface CreateTodoUseCase {

    Long create(String memberId, String text, boolean isCheck);
}
