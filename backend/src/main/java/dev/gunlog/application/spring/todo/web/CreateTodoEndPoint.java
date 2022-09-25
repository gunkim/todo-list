package dev.gunlog.application.spring.todo.web;

import dev.gunlog.usecase.todo.CreateTodoUseCase;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import java.security.Principal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/todo")
public class CreateTodoEndPoint {

    private final CreateTodoUseCase createTodoUseCase;

    public CreateTodoEndPoint(CreateTodoUseCase createTodoUseCase) {
        this.createTodoUseCase = createTodoUseCase;
    }

    @ApiOperation("할 일 등록")
    @ApiImplicitParams({@ApiImplicitParam(name = "text", value = "내용", dataType = "string"),
        @ApiImplicitParam(name = "isCheck", value = "체크여부", dataType = "boolean")})
    @PostMapping
    public void createTodo(@RequestBody TodoRequest dto, Principal principal) {
        createTodoUseCase.create(principal.getName(), dto.text, dto.isCheck);
    }

    public record TodoRequest(String text, boolean isCheck) {

    }
}
