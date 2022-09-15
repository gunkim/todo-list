package dev.gunlog.data.jpa;

import dev.gunlog.domain.member.Member;
import dev.gunlog.domain.todo.Todo;
import dev.gunlog.domain.todo.TodoRepository;
import dev.gunlog.domain.todo.Todos;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class TodoJpaRepository implements TodoRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    public TodoJpaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Todos findAll() {
        return new Todos(
            entityManager.createQuery("SELECT t FROM TodoEntity t", TodoEntity.class).getResultList().stream()
                .map(TodoEntity::toModel).toList());
    }

    @Override
    public Optional<Todo> findById(Long id) {
        return Optional.ofNullable(entityManager.find(TodoEntity.class, id)).map(TodoEntity::toModel);
    }

    @Override
    public Todo save(Todo todo) {
        TodoEntity entity = loadById(todo.id());
        if (entity == null) {
            entity = TodoEntity.from(todo);
            entityManager.persist(entity);
        } else {
            entity.update(todo);
            entityManager.merge(entity);
        }
        return entity.toModel();
    }

    @Override
    public Todo delete(Todo todo) {
        TodoEntity entity = entityManager.find(TodoEntity.class, todo.id());
        entityManager.remove(entity);
        return entity.toModel();
    }

    @Override
    public Todos findAllByMember(Member member) {
        var query = entityManager.createQuery("""
            SELECT t 
            FROM TodoEntity t JOIN MemberEntity m 
                 ON t.member = m 
            WHERE m.id = ?1
            """, TodoEntity.class);
        query.setParameter(1, member.id());

        return new Todos(query.getResultList().stream().map(TodoEntity::toModel).toList());
    }

    @Override
    public Optional<Todo> findByIdAndMember(Long id, Member member) {
        var query = entityManager.createQuery("""
            SELECT t 
            FROM TodoEntity t JOIN MemberEntity m
                 ON t.member = m
            WHERE t.id = ?1 
              and m.id = ?2
            """, TodoEntity.class);
        query.setParameter(1, id);
        query.setParameter(2, member.id());

        return Optional.ofNullable(query.getSingleResult()).map(TodoEntity::toModel);
    }

    private TodoEntity loadById(Long id) {
        if (id == null) {
            return null;
        }
        return entityManager.find(TodoEntity.class, id);
    }
}