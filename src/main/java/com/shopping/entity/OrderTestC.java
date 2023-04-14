package com.shopping.entity;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@SpringBootTest
@Transactional
public class OrderTestC extends EntityMapping {
    @Test
    @DisplayName("지연 로딩 테스트")
    public void lazyLoadingTest() {
        Order order = createOrder() ;

        // 1번째 상품의 아이디
        Long orderProductId = order.getOrderProducts().get(0).getId();

        em.flush();
        em.clear();

        OrderProduct orderProduct = orderProductRepository.findById(orderProductId).orElseThrow(EntityNotFoundException::new) ;

        System.out.println("Order Class : " + orderProduct.getOrder().getClass());
        System.out.println("=============================");

        orderProduct.getOrder().getOrderDate() ;
        System.out.println("=============================");

    }
}
