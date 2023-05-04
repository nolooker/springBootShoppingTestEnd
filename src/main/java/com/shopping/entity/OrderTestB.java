package com.shopping.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class OrderTestB extends EntityMapping{
    @Test
    @DisplayName("고아 객체 테스트")
    public void orphanRemovalTest(){
        Order order = super.createOrder() ;
        order.getOrderProducts().remove(0) ;
        em.flush();
    }
}
