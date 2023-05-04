package com.shopping.service;

import com.shopping.entity.Member;
import com.shopping.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service // for 비즈니스 로직 담당자
@Transactional
@RequiredArgsConstructor // final이나 @NotNull이 붙어 있는 변수에 생성자를 자동으로 만들어 줍니다.
public class MemberService implements UserDetailsService {
    private final MemberRepository memberRepository ;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email) ;
        if(member == null){ // 회원이 존재하지 않는 경우
            throw new UsernameNotFoundException(email) ;
        }
        return User.builder()
                .username(member.getEmail())
                .password(member.getPassword())
                .roles(member.getRole().toString())
                .build();
    }

    public Member saveMember(Member member){
        validateDuplicateMember(member) ;
        return memberRepository.save(member);
    }

    private void validateDuplicateMember(Member member) {
        Member findMember = memberRepository.findByEmail(member.getEmail()) ;
        if(findMember != null){
            throw new IllegalStateException("이미 가입된 회원입니다.") ;
        }
    }


}
