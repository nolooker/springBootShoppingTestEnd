package com.shopping.aoptest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// 로그인하고, http://localhost:8989/gooffice 실행
/*
아침에 출근하기 전에 할 일(Before)
세수를 합니다.
아침 밥을 먹습니다.

출근 한 다음에 회사에서(After)
점심을 먹습니다.
퇴급합니다.
*/

/*
OOP(Object Oriented Programming) : 객체 지향 프로그래밍
AOP(Aspect Oriented Programming) : 관점 지향 프로그래밍
Aspect : 여러 객체에 공통적으로 적용이 되는 관심 사항들
 */

/*
Inversion of Control(제어의 역전)
개발자가 직접 함수/메소드 등을 호출하지 않고, 그 주도권을 스프링에게 넘겨주는 것
스프링은 위임자(delegate) 또는 대행자 역할을 합니다.
*/

@RestController
public class AopController {

    @GetMapping(value = "/gooffice")
    public String goWorking(){

//        System.out.println("세수를 합니다.");
//        System.out.println("아침 밥을 먹습니다.");
//        System.out.println("출근하고.");
//        System.out.println("점심을 먹습니다.");
//        System.out.println("퇴근합니다.");

        return "사무실 출근" ;
    }
}
