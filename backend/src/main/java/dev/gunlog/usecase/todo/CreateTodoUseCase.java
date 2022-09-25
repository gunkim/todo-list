package dev.gunlog.usecase.todo;

public interface CreateTodoUseCase {

    Long create(String memberId, String text, boolean isCheck);
}
