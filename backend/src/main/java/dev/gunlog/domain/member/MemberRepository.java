package dev.gunlog.domain.member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    List<Member> findAll();

    Optional<Member> findById(Long id);

    Member save(Member member);

    Optional<Member> findByLoginId(String loginId);
}
