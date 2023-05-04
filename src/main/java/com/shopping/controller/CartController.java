package com.shopping.controller;

import com.shopping.dto.CartDetailDto;
import com.shopping.dto.CartOrderDto;
import com.shopping.dto.CartProductDto;
import com.shopping.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

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

        Long cartProductId = 0L ;

        try {

            cartProductId = cartService.addCart(cartProductDto, email) ;
            return new ResponseEntity<Long>(cartProductId, HttpStatus.OK) ;

        } catch (Exception err) {
            err.printStackTrace();
            return new ResponseEntity<String>(err.getMessage(), HttpStatus.BAD_REQUEST) ;
        }
    }

    @GetMapping(value = "/cart")
    public String orderHist(Principal principal, Model model) {

        String email = principal.getName();
        List<CartDetailDto> cartDetailDtoList = cartService.getCartList(email) ;

        model.addAttribute("cartProducts", cartDetailDtoList);

        return "cart/cartList" ;
    }

    @PatchMapping(value = "/cartProduct/{cartProductId}")
    public @ResponseBody ResponseEntity updateCartProduct(@PathVariable("cartProductId") Long cartProductId, int count, Principal principal){  // 카트에서 특정 상품의 수량 변경

        if (count <= 0) {
            return new ResponseEntity<String>("수량은 최소 1개 이상이어야 합니다.", HttpStatus.BAD_REQUEST) ;
        }


        String email = principal.getName();
        if (cartService.validateCartProduct(cartProductId, email) == false) {  // 권한 체크
            return new ResponseEntity<String>("수정 권한이 없습니다.", HttpStatus.FORBIDDEN) ;
        }

        cartService.updateCartProductCount(cartProductId, count);  // 장바구니 수량 변경하기
        return new ResponseEntity<Long>(cartProductId, HttpStatus.OK) ;

    }

    @DeleteMapping(value = "/cartProduct/{cartProductId}")
    public @ResponseBody ResponseEntity deleteCartProduct(@PathVariable("cartProductId") Long cartProductId, Principal principal){  // 카트에서 특정 상품 삭제

        String email = principal.getName();

        if (cartService.validateCartProduct(cartProductId, email) == false) {  // 권한 체크
            return new ResponseEntity<String>("삭제 권한이 없습니다.", HttpStatus.FORBIDDEN) ;
        }

        cartService.deleteCartProduct(cartProductId);  // 장바구니 상품 삭제하기
        return new ResponseEntity<Long>(cartProductId, HttpStatus.OK) ;

    }

    @PostMapping(value = "/cart/orders")
    public @ResponseBody ResponseEntity orderCartProduct(@RequestBody CartOrderDto cartOrderDto, Principal principal){

        List<CartOrderDto> cartOrderDtoList = cartOrderDto.getCartOrderDtoList() ;

        // 주문 상품 선택 여부 확인
        if (cartOrderDtoList == null || cartOrderDtoList.size() == 0) {
            return new ResponseEntity<String>("주문할 상품을 선택해 주세요.", HttpStatus.BAD_REQUEST) ;
        }

        String email = principal.getName();

        for (CartOrderDto dto : cartOrderDtoList) {
            // 주문 권한 체크하기
            boolean bool = cartService.validateCartProduct(dto.getCartProductId(), email);

            if (bool == false) {
                return new ResponseEntity<String>("주문 권한이 없습니다.", HttpStatus.FORBIDDEN) ;
            }
        }

        // 주문 로직을 호출하고 주문 아이디를 반환 받습니다.
        Long orderId = cartService.orderCartProduct(cartOrderDtoList, email) ;

        // 생성된 주문 번호와 함께 HTTP 응답 코드를 반환해 줍니다.
        return new ResponseEntity<Long>(orderId, HttpStatus.OK) ;

    }
}
