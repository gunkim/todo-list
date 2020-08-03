package com.gun.app.service;

import com.gun.app.domain.Todo;
import com.gun.app.dto.TodoRequestDTO;
import com.gun.app.dto.TodoResponseDTO;

import java.util.List;

/**
 * 할 일 비즈니스 로직 처리를 위한 서비스
 */
public interface TodoService {
    /**
     * 할 일 리스트 조회
     * @return List<TodoResponseDTO>
     */
    List<TodoResponseDTO> getTodoList();
    /**
     * 할 일 목록 작성
     */
    void createTodo(TodoRequestDTO dto);

    /**
     * 할 일 체크 목록 반전.
     */
    void setReverseCheckTodo(long id) throws IllegalArgumentException;

    /**
     * 할 일 삭제
     */
    void deleteTodo(long id) throws IllegalArgumentException;
}