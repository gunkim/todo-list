package dev.gunlog.data.jpa;

import dev.gunlog.domain.member.Member;
import dev.gunlog.domain.member.Role;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.jetbrains.annotations.NotNull;

@Entity
public class MemberEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_no")
    private Long id;

    @NotNull
    @Column(name = "member_id")
    private String loginId;

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

    protected MemberEntity() {
    }

    public MemberEntity(Long id, @NotNull String loginId, @NotNull String password, @NotNull String name,
        @NotNull Role role, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.role = role;
    }

    public static MemberEntity from(Member member) {
        return new MemberEntity(member.id(), member.loginId(), member.password(), member.name(), member.role(),
            member.createdDate(), member.updatedDate());
    }

    public Long getId() {
        return id;
    }

    @NotNull
    public String getLoginId() {
        return loginId;
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

    public void update(Member member) {
        this.password = member.password();
        this.name = member.name();
        this.role = member.role();
    }

    public Member toModel() {
        return new Member(id, loginId, password, name, role, getCreatedDate(), getModifiedDate());
    }
}
