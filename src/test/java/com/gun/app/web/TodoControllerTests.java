package com.gun.app.web;

import com.gun.app.domain.Todo;
import com.gun.app.domain.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class TodoControllerTests {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext ctx;

    @Autowired
    private TodoRepository todoRepository;

    private static final String API_URI = "/api/todo";

    @Before
    public void setUp(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))  // 필터 추가
                .alwaysDo(print())
                .build();

        IntStream.rangeClosed(1, 100).forEach(i -> {
            todoRepository.save(
                    Todo.builder()
                            .text(i+"번째 할 일 입력 테스트")
                            .isCheck(false)
                            .build()
            );
        });
    }

    @Test
    public void getTodoListTest() throws Exception {
        mockMvc.perform(get(API_URI+"/list").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }
    @Test
    public void setReverseCheckTodoTest() throws Exception {
        long id = todoRepository.findAll().get(0).getId();

        String uri = API_URI+"/"+id;
        mockMvc.perform(put(uri).contentType(MediaType.APPLICATION_JSON))
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
    public void deleteTodoTest() throws Exception {
        long id = todoRepository.findAll().get(0).getId();

        String uri = API_URI+"/"+id;
        mockMvc.perform(delete(uri).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

        Optional<Todo> optTodo = todoRepository.findById(id);

        if(optTodo.isPresent()){
           fail("해당 TODO 삭제에 실패하였습니다.");
        }
    }
    @Test
    public void createTodoTest() throws Exception {
        String insertText = "입력 테스트";
        mockMvc.perform(post(API_URI).contentType(MediaType.APPLICATION_JSON)
                .content("{\"text\":\"입력 테스트\"}")
        ).andExpect(status().isOk()).andDo(print());

        List<Todo> todoAllList = todoRepository.findAll();
        todoAllList.stream().forEach(todo -> {
            log.info("todo : "+todo.toString());
        });
        Todo todo = todoAllList.get(todoAllList.size() - 1);

        assertEquals(insertText, todo.getText());
    }
}
