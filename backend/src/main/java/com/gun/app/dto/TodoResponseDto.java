package com.gun.app.dto;

import com.gun.app.domain.entity.Todo;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * 할 일 비즈니스 처리 결과 반환을 위한 DTO
 */
@Getter
@ToString
public class TodoResponseDto {
    private long id;
    private String text;
    private boolean isCheck;
    @Builder
    public TodoResponseDto(Todo todo){
        this.id = todo.getId();
        this.text = todo.getText();
        this.isCheck = todo.isCheck();
    }
}