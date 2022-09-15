package dev.gunlog.application.spring.web.dto;

import dev.gunlog.domain.member.Role;

public record MemberResponseDto(String memberId, String password, Role role) {

}