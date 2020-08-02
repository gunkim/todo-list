package com.gun.app.dto;

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
    public TodoResponseDTO(long id, String text, boolean isCheck){
        this.id = id;
        this.text = text;
        this.isCheck = isCheck;
    }
}