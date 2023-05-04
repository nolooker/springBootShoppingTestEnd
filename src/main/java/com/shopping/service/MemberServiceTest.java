package com.shopping.service;

import com.shopping.dto.MemberFormDto;
import com.shopping.entity.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional // 테스트가 종료 되면, 자동으로 롤백하겠습니다.
public class MemberServiceTest {
    @Autowired
    MemberService memberService ;

    @Autowired(required = false)
    PasswordEncoder passwordEncoder ;

    private Member createMember() {
        // 화면에서 여러분이 기입한 데이터
        MemberFormDto dto = new MemberFormDto() ;
        dto.setEmail("qwert@naver.com");
        dto.setName("김기수");
        dto.setAddress("금천 가산동");
        dto.setPassword("1234");
        return Member.createMember(dto, passwordEncoder) ;
    }

    @Test
    @DisplayName("회원 가입 테스트")
    public void saveMember(){
        Member member = createMember() ; // 우리가 화면에서 기입한 값

        // 실제 데이터 베이스에 들어 간 값
        Member savedMember = memberService.saveMember(member) ;

        savedMember.setEmail("bbb@daum.net");

        Assertions.assertEquals(member.getEmail(), savedMember.getEmail());
        Assertions.assertEquals(member.getName(), savedMember.getName());

        System.out.println("결과 확인");
        System.out.println(savedMember.toString());
    }
}
