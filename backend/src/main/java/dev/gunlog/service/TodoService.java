package dev.gunlog.service;

import dev.gunlog.domain.entity.Member;
import dev.gunlog.domain.entity.Todo;
import dev.gunlog.domain.repository.MemberRepository;
import dev.gunlog.domain.repository.TodoRepository;
import dev.gunlog.dto.TodoRequestDto;
import dev.gunlog.dto.TodoResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;
    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public List<TodoResponseDto> getTodoList(String memberId) throws IllegalArgumentException{
        Member member = memberRepository.findByMemberId(memberId).orElseThrow(() -> new IllegalArgumentException("해당 멤버를 찾을 수 없습니다 member="+memberId));

        return todoRepository.findAllByMember(member).stream().map(TodoResponseDto::new).collect(Collectors.toList());
    }
    public void createTodo(String memberId, TodoRequestDto dto) throws IllegalArgumentException{
        Member optMember = memberRepository.findByMemberId(memberId).orElseThrow(() -> new IllegalArgumentException("해당 멤버를 찾을 수 없습니다 member="+memberId));

        todoRepository.save(dto.toEntity(optMember));
    }
    @Transactional
    public void setReverseCheckTodo(String memberId, long id) throws IllegalArgumentException{
        Member member = memberRepository.findByMemberId(memberId).orElseThrow(() -> new IllegalArgumentException("해당 멤버를 찾을 수 없습니다 member="+memberId));
        Todo todo = todoRepository.findByIdAndMember(id, member).orElseThrow(()-> new IllegalArgumentException("해당 할 일을 찾을 수 없습니다 id="+id));

        todo.updateTextAndCheck(todo.getText(), !todo.isCheck());
    }
    public void deleteTodo(String memberId, long id) throws IllegalArgumentException{
        Member member = memberRepository.findByMemberId(memberId).orElseThrow(()-> new IllegalArgumentException("해당 멤버를 찾을 수 없습니다 member="+memberId));
        Todo todo = todoRepository.findByIdAndMember(id, member).orElseThrow(()-> new IllegalArgumentException("해당 할 일을 찾을 수 없습니다 id"+id));

        todoRepository.delete(todo);
    }
}