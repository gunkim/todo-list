package com.gun.app.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * MemberRepository 테스트
 * @author gunkim
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MemberRepositoryTests {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Before
    public void before(){
        memberRepository.deleteAll();
    }

    @Test
    public void insertTest(){
        memberRepository.save(
                Member.builder()
                        .memberId("gunkim")
                        .password(passwordEncoder.encode("test"))
                        .build()
        );

        Member member = memberRepository.findAll().get(0);
        assertEquals(member.getMemberId(), "gunkim");
        assertTrue(passwordEncoder.matches("test", member.getPassword()));
    }
}