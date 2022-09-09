package dev.gunlog.domain.entity;

import dev.gunlog.domain.entity.common.BaseTimeEntity;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.jetbrains.annotations.NotNull;

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
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "member_id")
    private Member member;

    protected Todo() {
    }

    public Todo(long id, @NotNull String text, boolean isCheck, @NotNull Member member) {
        this.id = id;
        this.text = text;
        this.isCheck = isCheck;
        this.member = member;
    }

    public void updateText(String text) {
        this.text = text;
    }

    public void updateTextAndCheck(String text, boolean isCheck) {
        this.updateText(text);
        this.isCheck = isCheck;
    }

    public long getId() {
        return id;
    }

    @NotNull
    public String getText() {
        return text;
    }

    public boolean isCheck() {
        return isCheck;
    }

    @NotNull
    public Member getMember() {
        return member;
    }
}