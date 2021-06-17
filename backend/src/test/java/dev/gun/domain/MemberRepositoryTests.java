package dev.gun.domain;

import dev.gunlog.domain.entity.Member;
import dev.gunlog.domain.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MemberRepositoryTests {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

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
                        .password(passwordEncoder.encode("test"))
                        .build()
        );

        Member member = memberRepository.findAll().get(0);
        assertThat(member.getMemberId(), is(equalTo("gunkim")));
        assertTrue(passwordEncoder.matches("test", member.getPassword()));
    }
}