package com.gun.app.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gun.app.config.security.model.LoginDto;
import com.gun.app.domain.*;
import com.gun.app.dto.TodoRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.Assert.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * TodoController 테스트
 * @author gunkim
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TodoControllerTests {
    @LocalServerPort
    private int port;

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext ctx;

    @Autowired
    private TodoRepository todoRepository;
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private String baseUrl = "http://localhost:%d/api/todo";

    private String jwtToken;

    @Before
    public void setUp() throws Exception {
        todoRepository.deleteAll();
        memberRepository.deleteAll();
        this.baseUrl = String.format(baseUrl, port);

        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(ctx)
                .apply(springSecurity())
                .build();

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

        this.jwtToken =result.getResponse().getContentAsString();
    }
    @Test
    @WithMockUser(roles = "USER",username = "gunkim")
    public void getTodoListTest() throws Exception {

        String url = baseUrl+"/list";

        mockMvc.perform(get(url).header("Authorization", jwtToken).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }
    @Test
    @WithMockUser(roles = "USER",username = "gunkim")
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
    @WithMockUser(roles = "USER",username = "gunkim")
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
    @WithMockUser(roles = "USER",username = "gunkim")
    public void createTodoTest() throws Exception {
        TodoRequestDTO dto = TodoRequestDTO.builder()
                .text("입력 테스트")
                .build();

        mockMvc.perform(post(baseUrl).header("Authorization", jwtToken)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(dto))
        ).andExpect(status().isOk()).andDo(print());

        List<Todo> todoAllList = todoRepository.findAll();
        todoAllList.stream().forEach(todo -> {
            log.info("todo : "+todo.toString());
        });
        Todo todo = todoAllList.get(todoAllList.size() - 1);

        assertEquals(dto.getText(), todo.getText());
    }
}
