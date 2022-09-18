package dev.gunlog.data.jpa;

import static org.assertj.core.api.Assertions.assertThat;

import dev.gunlog.domain.member.Member;
import dev.gunlog.domain.member.Role;
import dev.gunlog.domain.todo.Todo;
import dev.gunlog.domain.todo.TodoRepository;
import dev.gunlog.domain.todo.Todos;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class TodoJpaRepositoryTests extends CommonDatabaseRepositoryTests {

    @Autowired
    private TodoRepository sut;

    private Todo todo;

    @BeforeEach
    void setup() {
        todo = sut.save(
            new Todo(null, "오늘의 할 일", false, new Member(null, "gunkim", "gunkim123", "거니거니", Role.USER, null, null),
                LocalDateTime.now(), null));
    }

    @Test
    @DisplayName("모든 할 일을 찾는다")
    void findAll() {
        Todos todos = sut.findAll();

        assertThat(todos.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("아이디로 할 일을 찾는다")
    void findById() {
        Todo todo = sut.findById(this.todo.id()).get();

        assertThat(todo).isEqualTo(this.todo);
    }

    @Test
    @DisplayName("할 일을 삭제한다")
    void delete() {
        sut.delete(todo);

        Optional<Todo> maybeTodo = sut.findById(todo.id());
        assertThat(maybeTodo.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("회원의 할 일을 모두 찾는다")
    void findAllByMember() {
        Todos todos = sut.findAllByMember(this.todo.member());

        assertThat(todos.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("아이디와 회원에 맞는 할 일을 찾는다")
    void findByIdAndMember() {
        Optional<Todo> maybeTodo = sut.findByIdAndMember(todo.id(), todo.member());

        assertThat(maybeTodo.isPresent()).isTrue();
    }
}