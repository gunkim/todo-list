package com.gun.app.dto;

import com.gun.app.domain.Member;
import com.gun.app.domain.Todo;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * 할 일 비즈니스 처리 결과 반환을 위한 DTO
 */
@Getter
@ToString
public class TodoResponseDTO {
    private long id;
    private String text;
    private boolean isCheck;
    @Builder
    public TodoResponseDTO(Todo todo){
        this.id = todo.getId();
        this.text = todo.getText();
        this.isCheck = todo.isCheck();
    }
}