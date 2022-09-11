package dev.gunlog.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import dev.gunlog.domain.member.Member;
import dev.gunlog.domain.member.MemberRepository;
import dev.gunlog.domain.member.Role;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FakeMemberRepositoryTests {

    private MemberRepository sut = new FakeMemberRepository();

    @BeforeEach
    void setup() {
        sut.save(new Member(1L, "login", "password", "gunkim", Role.USER, LocalDateTime.now(), null));
    }

    @Test
    void 모든_유저를_조회한다() {
        List<Member> members = sut.findAll();

        assertThat(members).hasSize(1);
    }

    @Test
    void 식별자로_유저를_조회한다() {
        assertDoesNotThrow(() -> sut.findById(1L).get());
    }

    @Test
    void 로그인_아이디로_유저를_조회한다() {
        assertDoesNotThrow(() -> sut.findByLoginId("login"));
    }
}
