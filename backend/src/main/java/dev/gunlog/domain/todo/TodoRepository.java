package dev.gunlog.domain.todo;

import dev.gunlog.domain.member.Member;
import java.util.List;
import java.util.Optional;

public interface TodoRepository {

    List<Todo> findAll();

    Optional<Todo> findById(Long id);

    Todo save(Todo todo);

    Todo delete(Todo todo);

    List<Todo> findAllByMember(Member member);

    Optional<Todo> findByIdAndMember(Long id, Member member);
}