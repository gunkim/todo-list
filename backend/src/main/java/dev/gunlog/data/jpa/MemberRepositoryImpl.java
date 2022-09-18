package dev.gunlog.data.jpa;

import dev.gunlog.domain.member.Member;
import dev.gunlog.domain.member.MemberRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class MemberRepositoryImpl implements MemberRepository {

    private final MemberDao memberDao;

    public MemberRepositoryImpl(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    @Override
    public List<Member> findAll() {
        return memberDao.findAll().stream()
            .map(MemberEntity::toModel)
            .toList();
    }

    @Override
    public Optional<Member> findById(Long id) {
        return memberDao.findById(id).map(MemberEntity::toModel);
    }

    @Override
    public Member save(Member member) {
        return memberDao.save(MemberEntity.from(member)).toModel();
    }

    @Override
    public Optional<Member> findByLoginId(String loginId) {
        return memberDao.findByLoginId(loginId).map(MemberEntity::toModel);
    }
}