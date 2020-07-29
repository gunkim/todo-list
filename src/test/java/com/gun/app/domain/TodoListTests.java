package com.gun.app.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;


@DataJpaTest
@RunWith(SpringRunner.class)
public class TodoListTests {
    @Autowired
    private TodoListRepository todoListRepository;

    @Before
    public void before(){
        todoListRepository.deleteAll();

        TodoList todoList = TodoList.builder()
                .text("입력 테스트").build();

        todoListRepository.save(todoList);
    }
    @Test
    public void 업데이트_테스트(){
        String updateTextStr = "수정 테스트";
        TodoList todoList = todoListRepository.findAll().get(0);
        todoList.update(updateTextStr);

        todoListRepository.save(todoList);

        todoList = todoListRepository.findAll().get(0);
        assertEquals(todoList.getText(), updateTextStr);
    }
    @Test
    public void 삭제_테스트(){
        TodoList todoList = todoListRepository.findAll().get(0);
        todoListRepository.delete(todoList);

        assertEquals(todoListRepository.findAll().size(), 0);
    }
}