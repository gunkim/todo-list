package com.gun.app.dto;

import com.gun.app.domain.Member;
import com.gun.app.domain.Role;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class MemberResponseDto {
    private String memberId;
    private String password;
    private Role role;

    public MemberResponseDto(Member member){
        this.memberId = member.getMemberId();
        this.password = member.getPassword();
        this.role = member.getRole();
    }
}