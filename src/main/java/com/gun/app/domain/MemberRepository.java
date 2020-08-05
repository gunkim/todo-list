package com.gun.app.domain;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 회원 관리 레포지토리
 */
public interface MemberRepository extends JpaRepository<Member, Long> {
}