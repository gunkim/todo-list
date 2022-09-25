package dev.gunlog.application.spring.todo.web;

import dev.gunlog.usecase.todo.CheckTodoUseCase;
import io.swagger.annotations.ApiOperation;
import java.security.Principal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/todos")
public class CheckTodoEndPoint {

    private final CheckTodoUseCase checkTodoUseCase;

    public CheckTodoEndPoint(CheckTodoUseCase checkTodoUseCase) {
        this.checkTodoUseCase = checkTodoUseCase;
    }

    @ApiOperation("할 일 체크 반전")
    @PutMapping("/{id}")
    public void check(@PathVariable long id, Principal principal) {
        checkTodoUseCase.check(principal.getName(), id);
    }
}
