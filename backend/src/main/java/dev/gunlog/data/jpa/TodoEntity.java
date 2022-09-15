package dev.gunlog.data.jpa;

import dev.gunlog.domain.todo.Todo;
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
public class TodoEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todo_id")
    private Long id;

    @NotNull
    @Column(name = "todo_text")
    private String text;

    @NotNull
    @Column(name = "todo_check")
    private boolean isCheck;

    @NotNull
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "member_id")
    private MemberEntity member;

    protected TodoEntity() {
    }

    public TodoEntity(Long id, @NotNull String text, boolean isCheck, @NotNull MemberEntity member) {
        this.id = id;
        this.text = text;
        this.isCheck = isCheck;
        this.member = member;
    }

    public static TodoEntity from(Todo todo) {
        return new TodoEntity(todo.id(), todo.text(), todo.isCheck(), MemberEntity.from(todo.member()));
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
    public MemberEntity getMember() {
        return member;
    }

    public Todo toModel() {
        return new Todo(id, text, isCheck, member.toModel(), getCreatedDate(), getModifiedDate());
    }

    public void update(Todo todo) {
        this.text = todo.text();
        this.isCheck = todo.isCheck();
    }
}