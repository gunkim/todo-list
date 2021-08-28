package dev.gunlog.domain.entity;

import dev.gunlog.domain.entity.common.BaseTimeEntity;
import lombok.*;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Todo extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todo_id")
    private long id;

    @NotNull
    @Column(name = "todo_text")
    private String text;

    @NotNull
    @Column(name = "todo_check")
    private boolean isCheck;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Todo(long id, @NotNull String text, boolean isCheck, @NotNull Member member) {
        this.id = id;
        this.text = text;
        this.isCheck = isCheck;
        this.member = member;
    }
    public void updateText(String text){
        this.text = text;
    }

    public void updateTextAndCheck(String text, boolean isCheck){
        this.updateText(text);
        this.isCheck = isCheck;
    }
}