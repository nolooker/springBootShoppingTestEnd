package com.shopping.dto;

import com.shopping.constant.OrderStatus;
import com.shopping.entity.Order;
import lombok.Getter;
import lombok.Setter;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class OrderHistDto {
    private Long orderId ; // 주문 아이디
    private String orderDate ; // 주문 일자
    private OrderStatus orderStatus ; // 주문 상태 정보

    // 주문 상품 리스트
    private List<OrderProductDto> orderProductDtoList = new ArrayList<>() ;

    public void addOrderProductDto(OrderProductDto orderProductDto){
        orderProductDtoList.add(orderProductDto);
    }

    public OrderHistDto(Order order) {
        this.orderId = order.getId() ;

        String pattern = "yyyy-MM-dd HH:mm" ;
        this.orderDate = order.getOrderDate().format(DateTimeFormatter.ofPattern(pattern));

        this.orderStatus = order.getOrderStatus() ;
    }
}
