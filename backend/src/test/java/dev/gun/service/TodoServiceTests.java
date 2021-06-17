package dev.gun.service;

import dev.gunlog.domain.entity.Member;
import dev.gunlog.domain.entity.Todo;
import dev.gunlog.domain.enums.Role;
import dev.gunlog.domain.repository.MemberRepository;
import dev.gunlog.domain.repository.TodoRepository;
import dev.gunlog.dto.TodoRequestDto;
import dev.gunlog.service.TodoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Fail.fail;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TodoServiceTests {
    @Autowired
    private TodoService todoService;

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

        todoRepository.save(
                Todo.builder()
                        .text("할 일 입력 테스트")
                        .isCheck(false)
                        .member(member)
                        .build()
        );
    }
    @DisplayName("좋아요 반전 테스트")
    @Test
    public void setReverseCheckTodoTest(){
        long id = todoRepository.findAll().get(0).getId();
        todoService.setReverseCheckTodo("gunkim", id);

        Todo todo = todoRepository.findAll().get(0);
        assertTrue(todo.isCheck());
    }
    @DisplayName("할 일 등록 테스트")
    @Test
    public void createTodoTest(){
        todoRepository.deleteAll();
        todoService.createTodo("gunkim",
                TodoRequestDto
                        .builder()
                        .text("입력 테스트")
                        .isCheck(true)
                        .build()
        );
        Todo todo = todoRepository.findAll().get(0);
        assertThat(todo.getText(), is(equalTo("입력 테스트")));
        assertTrue(todo.isCheck());
    }
    @DisplayName("할 일 삭제 테스트")
    @Test
    public void deleteTodoTest(){
        long id = todoRepository.findAll().get(0).getId();
        todoService.deleteTodo("gunkim", id);

        Optional<Todo> optTodo = todoRepository.findById(id);
        if(optTodo.isPresent()){
            fail("TODO 삭제 실패");
        }
    }
    @DisplayName("할 일 목록 조회 테스트")
    @Test
    public void getTodoListTest(){
        todoService.getTodoList("gunkim").stream().forEach(todoResponseDTO -> System.out.println(todoResponseDTO));
    }
}