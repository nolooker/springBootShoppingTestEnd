package com.shopping.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class CartOrderDto {

    private Long cartProductId ;

    // 장바구니에는 여러 개의 상품이 담기므로 리스트 컬렉션 되어야 합니다.
    private List<CartOrderDto> cartOrderDtoList ;

}
