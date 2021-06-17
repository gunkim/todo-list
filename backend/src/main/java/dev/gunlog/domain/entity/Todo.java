package dev.gunlog.domain.entity;

import dev.gunlog.domain.entity.common.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

/**
 * 할 일 목록 테이블
 */
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Todo extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
    private boolean isCheck;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Builder
    public Todo(long id, String text, boolean isCheck, Member member){
        this.id = id;
        this.text = text;
        this.isCheck = isCheck;
        this.member = member;
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