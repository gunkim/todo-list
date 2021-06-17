package com.gun.app.service;

import com.gun.app.dto.MemberResponseDto;

import java.nio.file.attribute.UserPrincipalNotFoundException;

/**
 * 회원 비즈니스 로직 처리 서비스
 */
public interface MemberService {
    /**
     * 아이디를 통해 찾은 멤버 정보 반환함
     * @param memberId
     * @return
     */
    public MemberResponseDto getMember(String memberId) throws UserPrincipalNotFoundException;
}
