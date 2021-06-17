package dev.gunlog.web;

import dev.gunlog.dto.TodoRequestDto;
import dev.gunlog.dto.TodoResponseDto;
import dev.gunlog.service.TodoService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * 할 일 API 컨트롤러
 */
@Slf4j
@RequestMapping("/api/todo")
@RestController
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @ApiOperation("할 일 목록 조회")
    @GetMapping("/list")
    public ResponseEntity<List<TodoResponseDto>> getTodoList(Principal principal) throws IllegalArgumentException{
        String memberId = principal.getName();
        return new ResponseEntity<>(todoService.getTodoList(memberId), HttpStatus.OK);
    }

    /**
     * 할 일 등록
     * @author gunkim
     * @param dto
     * @return resultMsg
     */
    @ApiOperation("할 일 등록")
    @ApiImplicitParams({
            @ApiImplicitParam(name="text", value="내용", dataType = "string"),
            @ApiImplicitParam(name="isCheck", value="체크여부", dataType = "boolean")
    })
    @PostMapping("")
    public ResponseEntity<String> createTodo(@RequestBody TodoRequestDto dto, Principal principal){
        String memberId = principal.getName();
        try{
            todoService.createTodo(memberId, dto);
        }catch(IllegalArgumentException e){
            return new ResponseEntity<>("FAILURE", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }
    @ApiOperation("할 일 삭제")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable long id, Principal principal){
        String memberId = principal.getName();
        try{
            todoService.deleteTodo(memberId, id);
        }catch(IllegalArgumentException e){
            return new ResponseEntity<>("FAILURE", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }
    @ApiOperation("할 일 체크 반전")
    @PutMapping("/{id}")
    public ResponseEntity<String> setReverseCheckTodo(@PathVariable long id, Principal principal){
        String memberId = principal.getName();
        try{
            todoService.setReverseCheckTodo(memberId, id);
        }catch(Exception e){
            return new ResponseEntity<>("FAILURE", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }
}