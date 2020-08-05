package com.gun.app.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

/**
 * MemberRepository 테스트
 * @author gunkim
 */
@DataJpaTest
@RunWith(SpringRunner.class)
public class MemberRepositoryTests {
    @Autowired
    private MemberRepository memberRepository;
    @Test
    public void insertTest(){
        memberRepository.save(
                Member.builder()
                        .memberId("test1234")
                        .password("test1234")
                        .build()
        );

        Member member = memberRepository.findAll().get(0);
        assertEquals(member.getMemberId(), "test1234");
        assertEquals(member.getPassword(), "test1234");
    }
}