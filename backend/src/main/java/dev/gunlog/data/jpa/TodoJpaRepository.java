package dev.gunlog.data.jpa;

import dev.gunlog.domain.member.Member;
import dev.gunlog.domain.todo.Todo;
import dev.gunlog.domain.todo.TodoRepository;
import dev.gunlog.domain.todo.Todos;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class TodoJpaRepository implements TodoRepository {

    private final TodoDao todoDao;

    public TodoJpaRepository(TodoDao todoDao) {
        this.todoDao = todoDao;
    }

    @Override
    public Todos findAll() {
        return new Todos(
            todoDao.findAll().stream()
            .map(TodoEntity::toModel)
            .toList()
        );
    }

    @Override
    public Optional<Todo> findById(Long id) {
        return todoDao.findById(id)
            .map(TodoEntity::toModel);
    }

    @Override
    public Todo save(Todo todo) {
        return todoDao.save(TodoEntity.from(todo)).toModel();
    }

    @Override
    public void delete(Todo todo) {
        todoDao.delete(TodoEntity.from(todo));
    }

    @Override
    public Todos findAllByMember(Member member) {
        return new Todos(
            todoDao.findAllByMember(MemberEntity.from(member)).stream()
                .map(TodoEntity::toModel)
                .toList()
        );
    }

    @Override
    public Optional<Todo> findByIdAndMember(Long id, Member member) {
        return todoDao.findByIdAndMember(id, MemberEntity.from(member))
            .map(TodoEntity::toModel);
    }
}