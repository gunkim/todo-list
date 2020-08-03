package com.gun.app.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 할 일 목록 테이블
 */
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Todo extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String text;

    private boolean isCheck;

    @Builder
    public Todo(long id, String text, boolean isCheck){
        this.id = id;
        this.text = text;
        this.isCheck = isCheck;
    }

    /**
     * 내용 업데이트를 위한 메소드
     * @param text
     */
    public void update(String text){
        this.text = text;
    }

    /**
     * 내용 및 check 수정을 위한 메소드
     * @param text
     * @param isCheck
     */
    public void update(String text, boolean isCheck){
        this.update(text);
        this.isCheck = isCheck;
    }
}