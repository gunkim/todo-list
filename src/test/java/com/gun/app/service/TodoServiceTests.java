package com.gun.app.service;

import com.gun.app.domain.Todo;
import com.gun.app.domain.TodoRepository;
import com.gun.app.dto.TodoRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class TodoServiceTests {
    @Autowired
    private TodoService todoService;

    @Autowired
    private TodoRepository todoRepository;

    @Before
    public void before(){
        todoRepository.deleteAll();

        todoRepository.save(
                Todo.builder()
                        .text("할 일 입력 테스트")
                        .isCheck(false)
                        .build()
        );
    }
    @Test
    public void setReverseCheckTodoTest(){
        long id = todoRepository.findAll().get(0).getId();
        todoService.setReverseCheckTodo(id);

        Todo todo = todoRepository.findAll().get(0);
        assertTrue(todo.isCheck());
    }
    @Test
    public void createTodoTest(){
        todoService.createTodo(
                TodoRequestDTO
                        .builder()
                        .text("입력 테스트")
                        .isCheck(true)
                        .build()
        );
        Todo todo = todoRepository.findAll().get(1);
        assertEquals(todo.getText(), "입력 테스트");
        assertTrue(todo.isCheck());
    }
    @Test
    public void deleteTodoTest(){
        long id = todoRepository.findAll().get(0).getId();
        todoService.deleteTodo(id);

        Optional<Todo> optTodo = todoRepository.findById(id);
        if(optTodo.isPresent()){
            fail("TODO 삭제 실패");
        }
    }
    @Test
    public void getTodoListTest(){
        todoService.getTodoList().stream().forEach(todoResponseDTO -> log.info(todoResponseDTO.toString()));
    }
}