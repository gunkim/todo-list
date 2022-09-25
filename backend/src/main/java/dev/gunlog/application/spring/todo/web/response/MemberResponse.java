package dev.gunlog.application.spring.todo.web.response;

import dev.gunlog.domain.member.Role;

public record MemberResponse(String memberId, String password, Role role) {

}