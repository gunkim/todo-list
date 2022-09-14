package dev.gunlog.application.spring.service;

import dev.gunlog.application.spring.web.dto.TodoRequestDto;
import dev.gunlog.data.jpa.MemberJpaRepository;
import dev.gunlog.data.jpa.TodoJpaRepository;
import dev.gunlog.domain.member.Member;
import dev.gunlog.domain.todo.Todo;
import dev.gunlog.domain.todo.usecase.CheckTodoUseCase;
import dev.gunlog.domain.todo.usecase.CreateTodoUseCase;
import dev.gunlog.domain.todo.usecase.DeleteTodoUseCase;
import dev.gunlog.domain.todo.usecase.FindTodoUseCase;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TodoService implements CreateTodoUseCase, FindTodoUseCase, CheckTodoUseCase, DeleteTodoUseCase {

    private final TodoJpaRepository todoJpaRepository;
    private final MemberJpaRepository memberJpaRepository;

    public TodoService(TodoJpaRepository todoJpaRepository, MemberJpaRepository memberJpaRepository) {
        this.todoJpaRepository = todoJpaRepository;
        this.memberJpaRepository = memberJpaRepository;
    }

    @Override
    public void create(String memberId, TodoRequestDto dto) {
        Member member = memberJpaRepository.findByLoginId(memberId)
            .orElseThrow(() -> new IllegalArgumentException("해당 멤버를 찾을 수 없습니다 member=" + memberId));

        Todo todo = new Todo(null, dto.text(), dto.isCheck(), member, LocalDateTime.now(), null);
        todoJpaRepository.save(todo);
    }

    @Override
    public List<Todo> find(String memberId) {
        Member member = memberJpaRepository.findByLoginId(memberId)
            .orElseThrow(() -> new IllegalArgumentException("해당 멤버를 찾을 수 없습니다 member=" + memberId));

        return todoJpaRepository.findAllByMember(member);
    }

    @Override
    public void check(String memberId, Long id) {
        Member member = memberJpaRepository.findByLoginId(memberId)
            .orElseThrow(() -> new IllegalArgumentException("해당 멤버를 찾을 수 없습니다 member=" + memberId));
        Todo todo = todoJpaRepository.findByIdAndMember(id, member)
            .orElseThrow(() -> new IllegalArgumentException("해당 할 일을 찾을 수 없습니다 id=" + id));

        todo.update(todo.text(), !todo.isCheck());
    }

    @Override
    public void delete(String memberId, long id) {
        Member member = memberJpaRepository.findByLoginId(memberId)
            .orElseThrow(() -> new IllegalArgumentException("해당 멤버를 찾을 수 없습니다 member=" + memberId));
        Todo todo = todoJpaRepository.findByIdAndMember(id, member)
            .orElseThrow(() -> new IllegalArgumentException("해당 할 일을 찾을 수 없습니다 id" + id));

        todoJpaRepository.delete(todo);
    }
}