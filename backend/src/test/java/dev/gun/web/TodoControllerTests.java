package dev.gun.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.gunlog.security.model.LoginDto;
import dev.gunlog.domain.entity.Member;
import dev.gunlog.domain.entity.Todo;
import dev.gunlog.domain.enums.Role;
import dev.gunlog.domain.repository.MemberRepository;
import dev.gunlog.domain.repository.TodoRepository;
import dev.gunlog.dto.TodoRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.assertj.core.api.Fail.fail;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class TodoControllerTests {
    @LocalServerPort
    private int port;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TodoRepository todoRepository;
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private String baseUrl = "http://localhost:%d/api/todo";

    private String jwtToken;

    @BeforeEach
    public void setUp() throws Exception {
        todoRepository.deleteAll();
        memberRepository.deleteAll();

        this.baseUrl = String.format(baseUrl, port);

        Member member = Member.builder()
                .memberId("gunkim")
                .password(passwordEncoder.encode("test"))
                .role(Role.USER)
                .name("STRONG MAN")
                .build();
        memberRepository.save(member);

        IntStream.rangeClosed(1, 100).forEach(i -> {
            todoRepository.save(
                    Todo.builder()
                            .text(i+"번째 할 일 입력 테스트")
                            .isCheck(false)
                            .member(member)
                            .build()
            );
        });
        LoginDto dto = new LoginDto("gunkim", "test");
        MvcResult result = mockMvc.perform(post("http://localhost:"+port+"/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(dto))
        ).andExpect(status().isOk()).andDo(print()).andReturn();

        this.jwtToken = result.getResponse().getContentAsString().replace("\"", "");
    }
    @Test
    @DisplayName("할 일 목록 조회 api 테스트")
    public void getTodoListTest() throws Exception {
        String url = baseUrl+"/list";

        mockMvc.perform(get(url).header("Authorization", jwtToken).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }
    @Test
    @DisplayName("좋아요 반전 api 테스트")
    public void setReverseCheckTodoTest() throws Exception {
        long id = todoRepository.findAll().get(0).getId();

        String url = baseUrl+"/"+id;

        mockMvc.perform(put(url).header("Authorization", jwtToken).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo((print()));

        Optional<Todo> optTodo = todoRepository.findById(id);
        if(optTodo.isPresent()){
            Todo todo = optTodo.get();
            assertTrue(todo.isCheck());
        }else{
            fail("해당 TODO가 없습니다.");
        }
    }
    @Test
    @DisplayName("할일 삭제 api 테스트")
    public void deleteTodoTest() throws Exception {
        long id = todoRepository.findAll().get(0).getId();

        String url = baseUrl+"/"+id;
        mockMvc.perform(delete(url).header("Authorization", jwtToken).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

        Optional<Todo> optTodo = todoRepository.findById(id);

        if(optTodo.isPresent()){
           fail("해당 TODO 삭제에 실패하였습니다.");
        }
    }
    @Test
    @DisplayName("할 일 등록 api 테스트")
    public void createTodoTest() throws Exception {
        TodoRequestDto dto = TodoRequestDto.builder()
                .text("입력 테스트")
                .build();

        mockMvc.perform(post(baseUrl).header("Authorization", jwtToken)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(dto))
        ).andExpect(status().isOk()).andDo(print());

        List<Todo> todoAllList = todoRepository.findAll();
        Todo todo = todoAllList.get(todoAllList.size() - 1);

        assertThat(dto.getText(), is(equalTo(todo.getText())));
    }
}
