package com.shopping.aoptest;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Order;
import org.springframework.stereotype.Component;

// @Order 사용시 @Before는 숫자가 작은 게 먼저 실행
// @Order 사용시 @After는 숫자가 큰 게 먼저 실행

@Aspect // 관심 사항으로 인식될
@Component // 컴포넌트입니다.
public class AopList {

    // AspectJ 표현식
    // execution(수식어패턴? 리턴타입패턴 패키지패턴?이름패턴 (파라미터패턴)
    @Before("execution(* com.shopping.aoptest..*.*(..))")
    @Order(value = 0)
    public void Wash(){
        System.out.println("세수를 합니다.");
    }

    @Before("execution(* com.shopping.aoptest..*.*(..))")
    @Order(value = 1)
    public void BreakFast(){
        System.out.println("아침밥을 먹습니다.");
    }

    @After("execution(* com.shopping.aoptest..*.*(..))")
    @Order(value = 1)
    public void Lunch(){
        System.out.println("점심을 먹습니다.");
    }

    @After("execution(* com.shopping.aoptest..*.*(..))")
    @Order(value = 0)
    public void Getoff(){
        System.out.println("퇴근을 합니다.");
    }

}
