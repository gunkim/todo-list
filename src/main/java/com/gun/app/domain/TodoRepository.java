package com.gun.app.domain;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 할 일 레포지토리
 */
public interface TodoRepository extends JpaRepository<Todo, Long> {
}