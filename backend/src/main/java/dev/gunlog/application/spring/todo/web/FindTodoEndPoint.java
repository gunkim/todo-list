package dev.gunlog.application.spring.todo.web;

import dev.gunlog.domain.todo.Todo;
import dev.gunlog.domain.todo.usecase.FindTodoUseCase;
import java.security.Principal;
import java.util.List;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/todos")
public class FindTodoEndPoint {

    private final FindTodoUseCase findTodoUseCase;

    public FindTodoEndPoint(FindTodoUseCase findTodoUseCase) {
        this.findTodoUseCase = findTodoUseCase;
    }

    @GetMapping
    public List<TodoResponse> find(@AuthenticationPrincipal Principal principal) throws IllegalArgumentException {
        return findTodoUseCase.find(principal.getName()).content().stream().map(TodoResponse::new).toList();
    }

    public record TodoResponse(long id, String text, boolean isCheck) {

        public TodoResponse(Todo todo) {
            this(todo.id(), todo.text(), todo.isCheck());
        }
    }
}
