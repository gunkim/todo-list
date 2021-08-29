package dev.gunlog.domain;

import dev.gunlog.domain.entity.Member;
import dev.gunlog.domain.entity.Todo;
import dev.gunlog.domain.enums.Role;
import dev.gunlog.domain.repository.TodoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class TodoRepositoryTests {
    @Autowired
    private TodoRepository todoRepository;

    @BeforeEach
    public void before(){
        todoRepository.deleteAll();
    }
    @Test
    @DisplayName("할 일 입력 테스트")
    public void todoSaveTest() {
        todoRepository.save(Todo.builder()
                .text("입력 테스트")
                .isCheck(true)
                .member(Member.builder()
                        .memberId("gunkim")
                        .password("test")
                        .role(Role.USER)
                        .name("gem")
                        .build())
                .build());

        Todo todo = todoRepository.findAll().get(0);
        assertThat(todo.getText()).isEqualTo("입력 테스트");
        assertThat(todo.isCheck()).isTrue();
        assertThat(todo.getMember()).isNotNull();
        assertThat(todo.getMember().getMemberId()).isEqualTo("gunkim");
    }
}