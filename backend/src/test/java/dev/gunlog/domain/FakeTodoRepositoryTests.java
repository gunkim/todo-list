package dev.gunlog.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import dev.gunlog.domain.member.Member;
import dev.gunlog.domain.member.Role;
import dev.gunlog.domain.todo.Todo;
import dev.gunlog.domain.todo.TodoRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FakeTodoRepositoryTests {

    private TodoRepository sut = new FakeTodoRepository();

    private Member member;
    private Todo todo;

    @BeforeEach
    void setup() {
        member = new Member(1L, "login", "password", "gunkim", Role.USER, LocalDateTime.now(), null);
        todo = sut.save(new Todo(1L, "오늘의 할 일", true, member, LocalDateTime.now(), null));
    }

    @Test
    void 모든_할_일을_조회한다() {
        List<Todo> todos = sut.findAll();
        assertThat(todos).hasSize(1);
    }

    @Test
    void 식별자로_할_일을_조회한다() {
        assertDoesNotThrow(() -> sut.findById(1L).get());
    }

    @Test
    void 할_일을_삭제한다() {
        Todo todo = sut.delete(this.todo);
        assertThat(todo).isEqualTo(this.todo);
    }
    @Test
    void 유저의_할_일_목록을_조회한다() {
        List<Todo> todos = sut.findAllByMember(member);
        assertThat(todos).hasSize(1);
    }
    @Test
    void 유저의_할_일을_조회한다() {
        assertDoesNotThrow(() -> sut.findByIdAndMember(1L, member).get());
    }
}
