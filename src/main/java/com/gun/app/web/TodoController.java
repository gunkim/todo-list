package com.gun.app.web;

import com.gun.app.dto.TodoResponseDTO;
import com.gun.app.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 할 일 API 컨트롤러
 */
@RequestMapping("/api/todo")
@RestController
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @GetMapping("/list")
    public ResponseEntity<List<TodoResponseDTO>> getTodoList() throws IllegalArgumentException{
        return new ResponseEntity<>(todoService.getTodoList(), HttpStatus.OK);
    }
}