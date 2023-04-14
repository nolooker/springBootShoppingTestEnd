package com.shopping.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@SpringBootTest
@Transactional
public class OrderTestA extends EntityMapping{

    @Test
    @DisplayName("영속성 전이 테스트")
    public void cascadeTest(){
        Order order = new Order(); // 주문 정보를 저장하고 있는 객체
        int data_length = 3 ; // 주문할 데이터 개수

        for (int i = 0; i < data_length; i++) {
            Product product = super.createProduct(); // 개별 상품
            productRepository.save(product) ;

            OrderProduct orderProduct = new OrderProduct() ;

            orderProduct.setProduct(product);
            orderProduct.setCount(10);
            orderProduct.setOrderPrice(1000);
            orderProduct.setOrder(order);

            order.getOrderProducts().add(orderProduct);

        }

        orderRepository.saveAndFlush(order);
        em.clear();

        System.out.println("송장 번호 : " + order.getId());
        // savedOrder : 데이터 베이스에 저장된 주문 정보를 담고 있는 객체
        Order savedOrder = orderRepository.findById(order.getId()).orElseThrow(EntityNotFoundException::new) ;

        System.out.println("savedOrder");
        System.out.println(savedOrder);

    }
}
