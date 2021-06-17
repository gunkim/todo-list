package com.gun.app.domain;

import com.gun.app.domain.entity.Member;
import com.gun.app.domain.entity.Todo;
import com.gun.app.domain.enums.Role;
import com.gun.app.domain.repository.MemberRepository;
import com.gun.app.domain.repository.TodoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TodoRepositoryTests {
    @Autowired
    private TodoRepository todoRepository;
    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    public void before(){
        todoRepository.deleteAll();
        memberRepository.deleteAll();

        Member member = Member.builder()
                .memberId("gunkim")
                .password("test")
                .role(Role.USER)
                .name("gem")
                .build();
        memberRepository.save(member);

        Todo todo = Todo.builder()
                .text("입력 테스트")
                .isCheck(true)
                .member(member)
                .build();
        todoRepository.save(todo);
    }
    @DisplayName("할 일 수정 테스트")
    @Test
    public void todoUpdateTest(){
        String updateTextStr = "수정 테스트";
        Todo todo = todoRepository.findAll().get(0);
        todo.update(updateTextStr, false);

        todoRepository.save(todo);

        todo = todoRepository.findAll().get(0);
        assertThat(todo.getText(), is(equalTo(updateTextStr)));
    }
    @DisplayName("할 일 삭제 테스트")
    @Test
    public void todoDeleteTest(){
        Todo todo = todoRepository.findAll().get(0);
        todoRepository.delete(todo);

        assertThat(todoRepository.findAll().size(), is(equalTo(0)));
    }
}