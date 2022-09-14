package dev.gunlog.domain.todo.usecase;

public interface DeleteTodoUseCase {

    void delete(String memberId, long id);
}
