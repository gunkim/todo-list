package dev.gunlog.data;

import dev.gunlog.domain.member.Member;
import dev.gunlog.domain.todo.Todo;
import dev.gunlog.domain.todo.TodoRepository;
import dev.gunlog.domain.todo.Todos;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class TodoRepositoryImpl implements TodoRepository {

    private final TodoJpaRepository todoJpaRepository;

    public TodoRepositoryImpl(TodoJpaRepository todoJpaRepository) {
        this.todoJpaRepository = todoJpaRepository;
    }

    @Override
    public Todos findAll() {
        return new Todos(
            todoJpaRepository.findAll().stream()
            .map(TodoEntity::toModel)
            .toList()
        );
    }

    @Override
    public Optional<Todo> findById(Long id) {
        return todoJpaRepository.findById(id)
            .map(TodoEntity::toModel);
    }

    @Override
    public Todo save(Todo todo) {
        return todoJpaRepository.save(TodoEntity.from(todo)).toModel();
    }

    @Override
    public void delete(Todo todo) {
        todoJpaRepository.delete(TodoEntity.from(todo));
    }

    @Override
    public Todos findAllByMember(Member member) {
        return new Todos(
            todoJpaRepository.findAllByMember(MemberEntity.from(member)).stream()
                .map(TodoEntity::toModel)
                .toList()
        );
    }

    @Override
    public Optional<Todo> findByIdAndMember(Long id, Member member) {
        return todoJpaRepository.findByIdAndMember(id, MemberEntity.from(member))
            .map(TodoEntity::toModel);
    }
}
