package dev.gunlog.application.spring.todo.web;

import dev.gunlog.domain.todo.usecase.DeleteTodoUseCase;
import io.swagger.annotations.ApiOperation;
import java.security.Principal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/todo")
public class DeleteTodoEndPoint {
    private final DeleteTodoUseCase deleteTodoUseCase;

    public DeleteTodoEndPoint(DeleteTodoUseCase deleteTodoUseCase) {
        this.deleteTodoUseCase = deleteTodoUseCase;
    }

    @ApiOperation("할 일 삭제")
    @DeleteMapping("/{todoId}")
    public void deleteTodo(@PathVariable long todoId, Principal principal) {
        deleteTodoUseCase.delete(principal.getName(), todoId);
    }
}
