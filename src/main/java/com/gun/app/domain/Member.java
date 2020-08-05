package com.gun.app.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 회원 관리 테이블
 */
@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String memberId;

    private String password;

    private String username;

    @Builder
    public Member(String memberId, String password, String username){
        this.memberId = memberId;
        this.password = password;
        this.username = username;
    }
}
