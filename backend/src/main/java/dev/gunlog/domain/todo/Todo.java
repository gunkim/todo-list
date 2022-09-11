package dev.gunlog.domain.todo;

import dev.gunlog.domain.member.Member;
import java.time.LocalDateTime;
import java.util.Objects;

public record Todo(Long id, String text, boolean isCheck, Member member, LocalDateTime createdDate,
                   LocalDateTime updatedDate) {

    public Todo update(String text, boolean isCheck) {
        return new Todo(id, text, isCheck, member, createdDate, updatedDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Todo todo = (Todo) o;
        return id.equals(todo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
