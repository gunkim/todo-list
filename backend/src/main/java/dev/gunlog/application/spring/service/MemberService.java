package dev.gunlog.application.spring.service;

import dev.gunlog.domain.member.Member;
import dev.gunlog.domain.member.MemberRepository;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member getMember(String memberId) throws UserPrincipalNotFoundException {
        Member member = memberRepository.findByLoginId(memberId)
            .orElseThrow(() -> new UserPrincipalNotFoundException("해당 아이디의 회원 정보를 찾을 수 없습니다 : " + memberId));
        return member;
    }
}
