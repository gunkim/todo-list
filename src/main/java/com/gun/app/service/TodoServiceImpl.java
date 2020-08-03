package com.gun.app.service;

import com.gun.app.domain.Todo;
import com.gun.app.domain.TodoRepository;
import com.gun.app.dto.TodoRequestDTO;
import com.gun.app.dto.TodoResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * 할 일 비즈니스 로직 처리를 위한 서비스 구현
 */
@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService{
    private final TodoRepository todoRepository;

    @Override
    public List<TodoResponseDTO> getTodoList() {
        List<TodoResponseDTO> todoList = new ArrayList<>();


        return todoRepository.findAll().stream()
                .map(todo -> TodoResponseDTO.builder()
                        .id(todo.getId())
                        .text(todo.getText())
                        .isCheck(todo.isCheck())
                        .build()).collect(Collectors.toList());
    }

    @Override
    public void createTodo(TodoRequestDTO dto) throws IllegalArgumentException{
        todoRepository.save(dto.toEntity());
    }

    @Override
    public void setReverseCheckTodo(long id) throws IllegalArgumentException{
        Todo todo = this.getTodo(todoRepository.findById(id));
        todo.update(todo.getText(), !todo.isCheck());
        todoRepository.save(todo);
    }

    @Override
    public void deleteTodo(long id) throws IllegalArgumentException{
        Todo todo = this.getTodo(todoRepository.findById(id));
        todoRepository.delete(todo);
    }
    private Todo getTodo(Optional<Todo> optTodo) throws IllegalArgumentException{
        if(optTodo == null || !optTodo.isPresent()){
            new IllegalArgumentException("파라미터 값을 다시 한번 확인해주세요");
        }
        return optTodo.get();
    }
}
