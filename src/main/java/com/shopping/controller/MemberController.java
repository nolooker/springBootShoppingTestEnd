package com.shopping.controller;

import com.shopping.dto.MemberFormDto;
import com.shopping.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService ;

    @GetMapping("/new")
    public String memberForm(Model model){

        // 타임 리프에서 사용할 객체 memberFormDto를 바인딩합니다.
        model.addAttribute("memberFormDto", new MemberFormDto()) ;

        return "/member/memberForm" ;
    }

    @PostMapping("/new")
    public String newMember(){

        System.out.println("포스트 방식 요청 들어옴");
        return "redirect:/" ; // 메인 페이지로 GoToPage
    }
}
