package com.gun.app.service;

import com.gun.app.domain.Member;
import com.gun.app.domain.MemberRepository;
import com.gun.app.dto.MemberResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    @Override
    public MemberResponseDto getMember(String memberId) throws UserPrincipalNotFoundException{
        Optional<Member> optionalMember = memberRepository.findByMemberId(memberId);

        if(!optionalMember.isPresent()){
            throw new UserPrincipalNotFoundException("해당 아이디의 회원 정보를 찾을 수 없습니다 : "+memberId);
        }
        return new MemberResponseDto(optionalMember.get());
    }
}