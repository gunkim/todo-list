package dev.gunlog.domain.entity;

import dev.gunlog.domain.entity.common.BaseTimeEntity;
import dev.gunlog.domain.enums.Role;
import lombok.*;
import org.jetbrains.annotations.NotNull;

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
    @Column(name = "member_no")
    private long id;

    @NotNull
    @Column(name = "member_id")
    private String memberId;

    @NotNull
    @Column(name = "member_password")
    private String password;

    @NotNull
    @Column(name = "member_name")
    private String name;

    @NotNull
    @Column(name = "member_role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public Member(long id, @NotNull String memberId, @NotNull String password, @NotNull String name, @NotNull Role role) {
        this.id = id;
        this.memberId = memberId;
        this.password = password;
        this.name = name;
        this.role = role;
    }
}
