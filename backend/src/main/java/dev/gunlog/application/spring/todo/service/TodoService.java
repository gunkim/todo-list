package dev.gunlog.application.spring.todo.service;

import dev.gunlog.domain.member.Member;
import dev.gunlog.domain.member.MemberRepository;
import dev.gunlog.domain.todo.Todo;
import dev.gunlog.domain.todo.TodoRepository;
import dev.gunlog.domain.todo.Todos;
import dev.gunlog.usecase.todo.CheckTodoUseCase;
import dev.gunlog.usecase.todo.CreateTodoUseCase;
import dev.gunlog.usecase.todo.DeleteTodoUseCase;
import dev.gunlog.usecase.todo.FindTodoUseCase;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TodoService implements CreateTodoUseCase, FindTodoUseCase, CheckTodoUseCase, DeleteTodoUseCase {

    private final TodoRepository todoRepository;
    private final MemberRepository memberRepository;

    public TodoService(TodoRepository todoRepository, MemberRepository memberRepository) {
        this.todoRepository = todoRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    public Long create(String memberId, String text, boolean isCheck) {
        Member member = findMember(memberId);
        Todo todo = new Todo(null, text, isCheck, member, LocalDateTime.now(), null);
        return todoRepository.save(todo).id();
    }

    @Override
    public Todos find(String memberId) {
        return todoRepository.findAllByMember(findMember(memberId));
    }

    @Override
    public void check(String memberId, Long id) {
        Member member = findMember(memberId);
        Todo todo = findMemberTodo(id, member);

        todoRepository.save(todo.update(todo.text(), !todo.isCheck()));
    }

    @Override
    public void delete(String memberId, long id) {
        Member member = findMember(memberId);
        Todo todo = findMemberTodo(id, member);

        todoRepository.delete(todo);
    }

    private Member findMember(String memberId) {
        return memberRepository.findByLoginId(memberId)
            .orElseThrow(() -> new IllegalArgumentException(String.format("해당 멤버를 찾을 수 없습니다 member_id: %s", memberId)));
    }

    private Todo findMemberTodo(Long todoId, Member member) {
        return todoRepository.findByIdAndMember(todoId, member).orElseThrow(() -> new IllegalArgumentException(
            String.format("해당 할 일을 찾을 수 없습니다 member_id: %s, todo_id: %s", member.id(), todoId)));
    }
}