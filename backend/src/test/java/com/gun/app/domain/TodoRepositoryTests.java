package com.gun.app.domain;

import com.gun.app.domain.entity.Member;
import com.gun.app.domain.entity.Todo;
import com.gun.app.domain.enums.Role;
import com.gun.app.domain.repository.MemberRepository;
import com.gun.app.domain.repository.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


/**
 * TodoRepository 테스트
 * @author gunkim
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class TodoRepositoryTests {
    @Autowired
    private TodoRepository todoRepository;
    @Autowired
    private MemberRepository memberRepository;

    @Before
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
    @Test
    public void updateTest(){
        String updateTextStr = "수정 테스트";
        Todo todo = todoRepository.findAll().get(0);
        todo.update(updateTextStr, false);

        todoRepository.save(todo);

        todo = todoRepository.findAll().get(0);
        assertEquals(todo.getText(), updateTextStr);
    }
    @Test
    public void deleteTest(){
        Todo todo = todoRepository.findAll().get(0);
        todoRepository.delete(todo);

        assertEquals(todoRepository.findAll().size(), 0);
    }
}