package com.shopping.repository;

import com.shopping.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    // 이메일을 이용하여 회원을 검색하기 하기 위한 쿼리 메소드입니다.
    Member findByEmail(String email) ;
}
