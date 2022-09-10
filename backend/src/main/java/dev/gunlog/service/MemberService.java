package dev.gunlog.service;

import dev.gunlog.domain.entity.Member;
import dev.gunlog.domain.repository.MemberRepository;
import dev.gunlog.dto.MemberResponseDto;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public MemberResponseDto getMember(String memberId) throws UserPrincipalNotFoundException {
        Member member = memberRepository.findByMemberId(memberId)
            .orElseThrow(() -> new UserPrincipalNotFoundException("해당 아이디의 회원 정보를 찾을 수 없습니다 : " + memberId));
        return new MemberResponseDto(member);
    }
}
