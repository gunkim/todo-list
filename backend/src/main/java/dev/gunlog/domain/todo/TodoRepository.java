package dev.gunlog.domain.todo;

import dev.gunlog.domain.member.Member;
import java.util.Optional;

public interface TodoRepository {

    Todos findAll();

    Optional<Todo> findById(Long id);

    Todo save(Todo todo);

    Todo delete(Todo todo);

    Todos findAllByMember(Member member);

    Optional<Todo> findByIdAndMember(Long id, Member member);
}