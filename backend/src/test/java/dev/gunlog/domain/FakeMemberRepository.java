package dev.gunlog.domain;

import dev.gunlog.domain.member.Member;
import dev.gunlog.domain.member.MemberRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class FakeMemberRepository implements MemberRepository {

    private final Map<Long, Member> content = new HashMap<>();

    @Override
    public List<Member> findAll() {
        return content.values().stream().toList();
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(content.get(id));
    }

    @Override
    public Member save(Member member) {
        return content.put(generateId(member), member);
    }

    @Override
    public Optional<Member> findByLoginId(String loginId) {
        return content.values().stream().filter(e -> e.loginId().equals(loginId)).findFirst();
    }

    private Long generateId(Member member) {
        if (member.id() != null) {
            return member.id();
        }
        return (long) content.size();
    }
}
