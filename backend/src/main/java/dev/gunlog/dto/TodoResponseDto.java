package dev.gunlog.dto;

import dev.gunlog.domain.entity.Todo;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

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