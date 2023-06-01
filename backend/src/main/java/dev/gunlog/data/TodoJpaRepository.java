package dev.gunlog.data;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoJpaRepository extends JpaRepository<TodoEntity, Long> {

    List<TodoEntity> findAllByMember(MemberEntity memberEntity);

    Optional<TodoEntity> findByIdAndMember(Long id, MemberEntity memberEntity);
}
