package com.shopping.controller;

import com.shopping.dto.MainProductDto;
import com.shopping.dto.ProductSearchDto;
import com.shopping.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final ProductService productService ;

    @RequestMapping(value = "/")
    public String main(ProductSearchDto dto, Optional<Integer> page, Model model){

        Pageable pageable = PageRequest.of(page.isPresent() ? page.get(): 0,3) ;

        if(dto.getSearchQuery() == null) {
            dto.setSearchQuery("");
        }

        Page<MainProductDto> products = productService.getMainProductPage(dto, pageable);

        model.addAttribute("products", products) ;
        model.addAttribute("searchDto", dto) ;
        model.addAttribute("maxPage", 5) ;

        return "main" ;

    }
}
