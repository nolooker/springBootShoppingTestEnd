package com.shopping.dto;

import com.shopping.entity.OrderProduct;
import lombok.Getter;
import lombok.Setter;

// 주문한 상품 1개에 대한 정보를 저장해주는 클래스
@Getter @Setter
public class OrderProductDto {
    private String name ;
    private int count ;
    private int orderPrice ;
    private String imageUrl ;

    public OrderProductDto(OrderProduct orderProduct, String imageUrl) {
        this.name = orderProduct.getProduct().getName() ;
        this.count = orderProduct.getCount() ;
        this.orderPrice = orderProduct.getOrderPrice() ;
        this.imageUrl = imageUrl;
    }
}
