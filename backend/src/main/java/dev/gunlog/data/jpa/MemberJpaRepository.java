package dev.gunlog.data.jpa;

import dev.gunlog.domain.member.Member;
import dev.gunlog.domain.member.MemberRepository;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class MemberJpaRepository implements MemberRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    public MemberJpaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Member> findAll() {
        return entityManager.createQuery("SELECT m FROM MemberEntity m", MemberEntity.class).getResultList().stream()
            .map(MemberEntity::toModel).toList();
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(loadById(id)).map(MemberEntity::toModel);
    }

    @Override
    public Member save(Member member) {
        MemberEntity entity = loadById(member.id());
        if (entity == null) {
            entity = MemberEntity.from(member);
            entityManager.persist(entity);
        } else {
            entity.update(member);
            entityManager.merge(entity);
        }
        return entity.toModel();
    }

    @Override
    public Optional<Member> findByLoginId(String loginId) {
        var query = entityManager.createQuery("SELECT m FROM MemberEntity m WHERE m.memberId = ?1", MemberEntity.class);
        query.setParameter(1, loginId);

        return query.getResultList().stream().findFirst().map(MemberEntity::toModel);
    }

    private MemberEntity loadById(Long id) {
        if (id == null) {
            return null;
        }
        return entityManager.find(MemberEntity.class, id);
    }
}