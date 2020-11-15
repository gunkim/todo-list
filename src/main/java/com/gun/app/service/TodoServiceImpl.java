package com.gun.app.service;

import com.gun.app.domain.entity.Member;
import com.gun.app.domain.repository.MemberRepository;
import com.gun.app.domain.entity.Todo;
import com.gun.app.domain.repository.TodoRepository;
import com.gun.app.dto.TodoRequestDto;
import com.gun.app.dto.TodoResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 할 일 비즈니스 로직 처리를 위한 서비스 구현
 */
@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService{
    private final TodoRepository todoRepository;
    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    @Override
    public List<TodoResponseDto> getTodoList(String memberId) throws IllegalArgumentException{
        Member member = memberRepository.findByMemberId(memberId).orElseThrow(() -> new IllegalArgumentException("해당 멤버를 찾을 수 없습니다 member="+memberId));

        return todoRepository.findAllByMember(member).stream().map(TodoResponseDto::new).collect(Collectors.toList());
    }

    @Override
    public void createTodo(String memberId, TodoRequestDto dto) throws IllegalArgumentException{
        Member optMember = memberRepository.findByMemberId(memberId).orElseThrow(() -> new IllegalArgumentException("해당 멤버를 찾을 수 없습니다 member="+memberId));

        todoRepository.save(dto.toEntity(optMember));
    }

    @Override
    public void setReverseCheckTodo(String memberId, long id) throws IllegalArgumentException{
        Member member = memberRepository.findByMemberId(memberId).orElseThrow(() -> new IllegalArgumentException("해당 멤버를 찾을 수 없습니다 member="+memberId));
        Todo todo = todoRepository.findByIdAndMember(id, member).orElseThrow(()-> new IllegalArgumentException("해당 할 일을 찾을 수 없습니다 id="+id));

        todo.update(todo.getText(), !todo.isCheck());
        todoRepository.save(todo);
    }

    @Override
    public void deleteTodo(String memberId, long id) throws IllegalArgumentException{
        Member member = memberRepository.findByMemberId(memberId).orElseThrow(()-> new IllegalArgumentException("해당 멤버를 찾을 수 없습니다 member="+memberId));
        Todo todo = todoRepository.findByIdAndMember(id, member).orElseThrow(()-> new IllegalArgumentException("해당 할 일을 찾을 수 없습니다 id"+id));

        todoRepository.delete(todo);
    }
}
