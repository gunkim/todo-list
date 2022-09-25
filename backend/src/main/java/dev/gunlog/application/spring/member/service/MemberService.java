package dev.gunlog.application.spring.member.service;

import dev.gunlog.domain.member.Member;
import dev.gunlog.domain.member.MemberRepository;
import dev.gunlog.usecase.member.FindMemberUseCase;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberService implements FindMemberUseCase {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Member findLoginId(String loginId) throws UserPrincipalNotFoundException {
        return memberRepository.findByLoginId(loginId)
            .orElseThrow(() -> new UserPrincipalNotFoundException(String.format("해당 아이디의 회원 정보를 찾을 수 없습니다. (member_id : %s)", loginId)));
    }
}
