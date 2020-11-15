package com.gun.app.domain.entity;

import com.gun.app.domain.entity.common.BaseTimeEntity;
import com.gun.app.domain.enums.Role;
import lombok.*;

import javax.persistence.*;

/**
 * 회원 관리 테이블
 */
@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String memberId;

    private String password;

    private String name;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public Member(String memberId, String password, String name, Role role){
        this.memberId = memberId;
        this.password = password;
        this.name = name;
        this.role = role;
    }
}
