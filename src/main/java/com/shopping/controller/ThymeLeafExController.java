package com.shopping.controller;

import com.shopping.dto.ProductDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/thymeleaf")
public class ThymeLeafExController {
    // http://localhost:8989/thymeleaf/ex01 요청이 들어오면
    @GetMapping(value = "/ex01")
    public String thymeleafExample01(Model model){ // 이 메소드가 처리해 줍니다.
        model.addAttribute("data", "타임 리프 1번째 예시입니다.");

        // thymeleafEx 폴더의 viewEx01.html 파일로 이동할께요.
        return "thymeleafEx/viewEx01" ;
    }

    @GetMapping(value = "/ex02")
    public String thymeleafExample02(Model model){
        ProductDto bean = new ProductDto() ;
        bean.setDescription("참 맛있어요.");
        bean.setName("사과");
        bean.setPrice(1234);
        bean.setRegDate(LocalDateTime.now());
        bean.setUpdateDate(LocalDateTime.now());

        model.addAttribute("bean", bean) ;
        return "thymeleafEx/viewEx02" ;
    }

    @GetMapping(value = "/ex03")
    public String thymeleafExample03(Model model){
        List<ProductDto> beanList = new ArrayList<ProductDto>();

        for (int i = 0; i < 10; i++) {
            ProductDto bean = new ProductDto() ;
            bean.setDescription("참 맛있어요.");
            bean.setName("사과" + i);
            bean.setPrice(1234);
            bean.setRegDate(LocalDateTime.now());
            bean.setUpdateDate(LocalDateTime.now());

            beanList.add(bean) ;
        }

        model.addAttribute("beanList", beanList) ;
        return "thymeleafEx/viewEx03" ;
    }

    @GetMapping(value = "/ex04")
    public String thymeleafExample04(Model model){
        List<ProductDto> beanList = new ArrayList<ProductDto>();

        for (int i = 0; i < 10; i++) {
            ProductDto bean = new ProductDto() ;
            bean.setDescription("참 맛있어요.");
            bean.setName("오렌지" + i);
            bean.setPrice(1234);
            bean.setRegDate(LocalDateTime.now());
            bean.setUpdateDate(LocalDateTime.now());

            beanList.add(bean) ;
        }

        model.addAttribute("beanList", beanList) ;
        return "thymeleafEx/viewEx04" ;
    }

    @GetMapping(value = "/ex05")
    public String thymeleafExample05(Model model){
        List<ProductDto> beanList = new ArrayList<ProductDto>();

        for (int i = 0; i < 10; i++) {
            ProductDto bean = new ProductDto() ;
            bean.setDescription("참 맛있어요.");
            bean.setName("사과" + i);
            bean.setPrice(1234);
            bean.setRegDate(LocalDateTime.now());
            bean.setUpdateDate(LocalDateTime.now());

            beanList.add(bean) ;
        }

        model.addAttribute("beanList", beanList) ;
        return "thymeleafEx/viewEx05" ;
    }

    @GetMapping(value = "/ex06")
    public String thymeleafExample06(Model model){
        return "thymeleafEx/viewEx06" ;
    }

    @GetMapping(value = "/ex07")
    public String thymeleafExample07(String param1, String param2, Model model){
        if(param1 == null){param1 = "호호호";}
        if(param2 == null){param2 = "크크크";}

        model.addAttribute("param1", param1) ;
        model.addAttribute("param2", param2) ;

        return "thymeleafEx/viewEx07" ;
    }

    @GetMapping(value = "/ex08")
    public String thymeleafExample08(Model model){
        return "thymeleafEx/viewEx08" ;
    }
}
