package dev.gunlog.dto;

import dev.gunlog.domain.entity.Member;
import dev.gunlog.domain.entity.Todo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 할 일 비즈니스 처리 요청을 위한 DTO
 */
@Getter
@ToString
@NoArgsConstructor
public class TodoRequestDto {
    private long id;
    private String text;
    private boolean isCheck;

    @Builder
    public TodoRequestDto(long id, String text, boolean isCheck){
        this.id = id;
        this.text = text;
        this.isCheck = isCheck;
    }
    public Todo toEntity(Member member){
        return Todo.builder()
                .text(text)
                .isCheck(isCheck)
                .member(member)
                .build();
    }
}