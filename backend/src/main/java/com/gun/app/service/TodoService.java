package com.gun.app.service;

import com.gun.app.dto.TodoRequestDto;
import com.gun.app.dto.TodoResponseDto;

import java.util.List;

/**
 * 할 일 비즈니스 로직 처리를 위한 서비스
 */
public interface TodoService {
    /**
     * 할 일 리스트 조회
     * @return List<TodoResponseDTO>
     */
    List<TodoResponseDto> getTodoList(String memberId);
    /**
     * 할 일 목록 작성
     */
    void createTodo(String memberId, TodoRequestDto dto);

    /**
     * 할 일 체크 목록 반전.
     */
    void setReverseCheckTodo(String memberId, long id) throws IllegalArgumentException;

    /**
     * 할 일 삭제
     */
    void deleteTodo(String memberId, long id) throws IllegalArgumentException;
}