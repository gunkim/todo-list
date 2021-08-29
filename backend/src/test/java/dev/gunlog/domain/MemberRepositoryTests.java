package dev.gunlog.domain;

import dev.gunlog.domain.entity.Member;
import dev.gunlog.domain.enums.Role;
import dev.gunlog.domain.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class MemberRepositoryTests {
    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    public void before(){
        memberRepository.deleteAll();
    }

    @DisplayName("회원 저장 테스트")
    @Test
    public void memberSaveTest(){
        memberRepository.save(
                Member.builder()
                        .memberId("gunkim")
                        .name("test user")
                        .password("test")
                        .role(Role.USER)
                        .build()
        );

        Member member = memberRepository.findAll().get(0);
        assertThat(member.getMemberId()).isEqualTo("gunkim");
        assertThat(member.getPassword()).isEqualTo("test");
        assertThat(member.getName()).isEqualTo("test user");
        assertThat(member.getRole()).isEqualTo(Role.USER);
    }
}