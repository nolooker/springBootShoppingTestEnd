package com.shopping.service;

import com.shopping.entity.Member;
import com.shopping.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service // for 비지니스 로직 담당자
@Transactional // org.springframework
@RequiredArgsConstructor // final이나 @NotNull이 붙어 있는 변수에 생성자를 자동으로 만들어 줍니다.
public class MemberService {

    private final MemberRepository memberRepository ;

    public Member saveMember(Member member) {
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }

    private void validateDuplicateMember(Member member){
        Member findMember = memberRepository.findByEmail(member.getEmail());
        if (findMember != null) {
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }
}
