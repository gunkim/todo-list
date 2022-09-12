package dev.gunlog.data.jpa;

import static org.assertj.core.api.Assertions.assertThat;

import dev.gunlog.domain.member.Member;
import dev.gunlog.domain.member.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MemberJpaRepositoryTests extends CommonDatabaseRepositoryTests {

    private MemberJpaRepository memberJpaRepository;

    private Member member;

    @BeforeEach
    void setup() {
        memberJpaRepository = new MemberJpaRepository(entityManager);
        member = memberJpaRepository.save(new Member(null, "gunkim", "gunkim123", "거니거니", Role.USER, null, null));
    }

    @Test
    @DisplayName("모든 회원을 찾는다")
    void findAll() {
        Member findMember = memberJpaRepository.findAll().get(0);
        assertThat(findMember).isEqualTo(member);
    }

    @Test
    @DisplayName("아이디로 회원을 찾는다")
    void findById() {
        Member findMember = memberJpaRepository.findById(member.id()).get();
        assertThat(findMember).isEqualTo(member);
    }

    @Test
    @DisplayName("로그안 아이디로 회원을 찾는다")
    void findByLoginId() {
        Member findMember = memberJpaRepository.findByLoginId("gunkim").get();

        assertThat(findMember).isEqualTo(member);
    }
}
