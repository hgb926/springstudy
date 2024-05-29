package com.study.springstudy.springmvc.chap05.mapper;

import com.study.springstudy.springmvc.chap05.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class MemberMapperTest {

    @Autowired
    private MemberMapper mapper;

    @Test
    @DisplayName("회원가입 성공")
    void save() {
        Member member = Member.builder()
                .account("kuromi")
                .password("abd1234!")
                .name("쿠로미")
                .email("kuromi@gmail.com")
                .build();
        boolean flag = mapper.save(member);
        assertTrue(flag);
    }

    @Test
    @DisplayName("kuromi 계정명 조회시 쿠로미가 나와야 한다.")
    void findOne() {
        Member foundMember = mapper.findOne("kuromi");
        System.out.println(foundMember.getName());
        assertEquals("쿠로미", foundMember.getName());
    }

    @Test
    @DisplayName("계정명이 kuromi인 회원은 중복확인 결과가 true이다.")
    void existsById() {
        boolean flag = mapper.existsById("account", "kuromi");
        System.out.println("flag = " + flag);
        assertTrue(flag);
    }

    @Test
    @DisplayName("계정명이 newJeans 회원은 중복확인 결과가 false이다.")
    void existsById2() {
        boolean flag = mapper.existsById("account", "newJeans");
        System.out.println("flag = " + flag);
        assertFalse(flag);
    }

}