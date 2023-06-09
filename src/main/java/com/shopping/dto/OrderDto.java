package com.shopping.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter @Setter
public class OrderDto {
    // 상세 페이지에서 넘어 오는 id와 구매 수량
    @NotNull(message = "상품 아이디는 필수 입력 값입니다.")
    private Long productId ;

    @Min(value = 1, message = "최소 주문량은 1개입니다.")
    @Max(value = 999, message = "최대 주문량은 999개입니다.")
    private int count ;
}
