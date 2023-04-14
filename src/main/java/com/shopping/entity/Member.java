package com.shopping.entity;

import com.shopping.constant.Role;
import com.shopping.dto.MemberFormDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Table(name = "members")
@Getter @Setter @ToString
public class Member extends BaseEntity{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;

    private String name ;

    @Column(unique = true)
    private String email ;

    private String password ;

    private String address ;

    @Enumerated(EnumType.STRING)
    private Role role ;

    // 화면에서 넘어오는 dto와 비번 암호화 객체를 이용하여 Member entity 객체 생성하는 메소드
    public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder){

        Member member = new Member();

        member.setName(memberFormDto.getName());
        member.setEmail(memberFormDto.getEmail());
        member.setAddress(memberFormDto.getAddress());

        String password = passwordEncoder.encode(memberFormDto.getPassword());
        member.setPassword(password);

//        member.setPassword(memberFormDto.getPassword());

        member.setRole(Role.USER);

        return member ;

    }

}
