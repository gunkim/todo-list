package com.gun.app.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * 회원 관리 레포지토리
 */
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByMemberId(String memberId);
}