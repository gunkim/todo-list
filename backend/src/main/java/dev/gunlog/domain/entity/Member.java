package dev.gunlog.domain.entity;

import dev.gunlog.domain.entity.common.BaseTimeEntity;
import dev.gunlog.domain.enums.Role;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.jetbrains.annotations.NotNull;

@Entity
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

    protected Member() {
    }

    public Member(long id, @NotNull String memberId, @NotNull String password, @NotNull String name, @NotNull Role role) {
        this.id = id;
        this.memberId = memberId;
        this.password = password;
        this.name = name;
        this.role = role;
    }

    public long getId() {
        return id;
    }

    @NotNull
    public String getMemberId() {
        return memberId;
    }

    @NotNull
    public String getPassword() {
        return password;
    }

    @NotNull
    public String getName() {
        return name;
    }

    @NotNull
    public Role getRole() {
        return role;
    }
}
