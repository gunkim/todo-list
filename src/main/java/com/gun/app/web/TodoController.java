package com.gun.app.web;

import com.gun.app.dto.TodoRequestDTO;
import com.gun.app.dto.TodoResponseDTO;
import com.gun.app.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * 할 일 API 컨트롤러
 */
@Slf4j
@RequestMapping("/api/todo")
@RestController
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @GetMapping("/list")
    public ResponseEntity<List<TodoResponseDTO>> getTodoList() throws IllegalArgumentException{
        return new ResponseEntity<>(todoService.getTodoList(), HttpStatus.OK);
    }
    @PostMapping("")
    public ResponseEntity<String> createTodo(@RequestBody TodoRequestDTO dto){
        log.info("테스트================");
        log.info(dto.toString());
        try{
            todoService.createTodo(dto);
        }catch(IllegalArgumentException e){
            return new ResponseEntity<>("FAILURE", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable long id){
        try{
            todoService.deleteTodo(id);
        }catch(IllegalArgumentException e){
            return new ResponseEntity<>("FAILURE", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }
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