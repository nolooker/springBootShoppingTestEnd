package com.shopping.entity;

import com.shopping.constant.ProductStatus;
import com.shopping.repository.MemberRepository;
import com.shopping.repository.OrderProductRepository;
import com.shopping.repository.OrderRepository;
import com.shopping.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;

public class EntityMapping {
    @Autowired
    ProductRepository productRepository ;
    @Autowired
    OrderRepository orderRepository ;
    @PersistenceContext
    EntityManager em ;
    @Autowired
    OrderProductRepository orderProductRepository ;

    protected Product createProduct() {
        Product product = new Product() ;
        product.setName("블루베리");
        product.setPrice(10000);
        product.setDescription("맛있어요");
        product.setProductStatus(ProductStatus.SELL);
        product.setStock(100);
        product.setRegDate(LocalDateTime.now());
        product.setUpdateDate(LocalDateTime.now());

        return product ;
    }

    @Autowired
    MemberRepository memberRepository ;

    protected Order createOrder() {
        Order order = new Order() ;
        for (int i = 0; i < 3 ; i++) {
            Product product = this.createProduct();
            productRepository.save(product) ;

            OrderProduct orderProduct = new OrderProduct() ;

            orderProduct.setProduct(product);
            orderProduct.setCount(10);
            orderProduct.setOrderPrice(1000);
            orderProduct.setOrder(order);

            order.getOrderProducts().add(orderProduct) ;
        }

        Member member = new Member() ;
        memberRepository.save(member) ;

        order.setMember(member);
        orderRepository.save(order) ;

        return order ;
    }
}
