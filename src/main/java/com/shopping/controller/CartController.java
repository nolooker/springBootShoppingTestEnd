package com.shopping.controller;

import com.shopping.dto.CartProductDto;
import com.shopping.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService ;

    @PostMapping(value = "/cart")
    public @ResponseBody ResponseEntity addCart(@RequestBody @Valid CartProductDto cartProductDto, BindingResult error, Principal principal) {

        // cartProductDto : 장바구니에 담을 상품의 정보
        if (error.hasErrors()){
            StringBuilder sb = new StringBuilder() ;

            List<FieldError> fieldErrors = new ArrayList<>();

            for (FieldError err : fieldErrors){
                sb.append(err.getDefaultMessage()) ;
            }

            return new ResponseEntity<String>(sb.toString(), HttpStatus.BAD_REQUEST) ;
        }

        String email = principal.getName() ;

        Long cartProdcutId = 0L ;

        try {

            cartProdcutId = cartService.addCart(cartProductDto, email) ;
            return new ResponseEntity<Long>(cartProdcutId, HttpStatus.OK) ;

        } catch (Exception err) {
            err.printStackTrace();
            return new ResponseEntity<String>(err.getMessage(), HttpStatus.BAD_REQUEST) ;
        }

    }
}
