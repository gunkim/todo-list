package com.gun.app.web;

import com.gun.app.dto.TodoRequestDTO;
import com.gun.app.dto.TodoResponseDTO;
import com.gun.app.service.TodoService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<TodoResponseDTO>> getTodoList() throws IllegalArgumentException{
        return new ResponseEntity<>(todoService.getTodoList(), HttpStatus.OK);
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
    public ResponseEntity<String> createTodo(@RequestBody TodoRequestDTO dto){
        try{
            todoService.createTodo(dto);
        }catch(IllegalArgumentException e){
            return new ResponseEntity<>("FAILURE", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }
    @ApiOperation("할 일 삭제")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable long id){
        try{
            todoService.deleteTodo(id);
        }catch(IllegalArgumentException e){
            return new ResponseEntity<>("FAILURE", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }
//    @ApiOperation("할 일 체크 반전")
//    @ApiImplicitParam(name="id", value="idx번호")
    @PutMapping("/{id}")
    public ResponseEntity<String> setReverseCheckTodo(@PathVariable long id){
        try{
            todoService.setReverseCheckTodo(id);
        }catch(Exception e){
            return new ResponseEntity<>("FAILURE", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }
}