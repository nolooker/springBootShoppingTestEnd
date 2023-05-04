package com.shopping.service;

import com.shopping.dto.OrderDto;
import com.shopping.dto.OrderHistDto;
import com.shopping.dto.OrderProductDto;
import com.shopping.entity.*;
import com.shopping.repository.MemberRepository;
import com.shopping.repository.OrderRepository;
import com.shopping.repository.ProductImageRepository;
import com.shopping.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {
    private final ProductRepository productRepository ;

    private final MemberRepository memberRepository;

    private final OrderRepository orderRepository;

    // email 정보와 주문 정보(OrderDto)를 이용하여 주문 로직을 구함
    public Long order(OrderDto orderDto, String email){

        // 어떤 상품인가요?
        Product product = productRepository.findById(orderDto.getProductId()).orElseThrow(EntityNotFoundException::new) ;
        Member member = memberRepository.findByEmail(email) ;
        List<OrderProduct> orderProductList = new ArrayList<>();
        OrderProduct orderProduct = OrderProduct.createOrderProduct(product, orderDto.getCount()) ;
        orderProductList.add(orderProduct);
        Order order = Order.createOrder(member, orderProductList) ;
        orderRepository.save(order);
        return order.getId() ;
    }

    private final ProductImageRepository productImageRepository ;

    public Page<OrderHistDto> getOrderList(String email, Pageable pageable) {

        List<Order> orders = orderRepository.findOrders(email, pageable) ;
        Long totalCount = orderRepository.countOrder(email) ;

        List<OrderHistDto> orderHistDtos = new ArrayList<>() ;

        for (Order order: orders) {  // 주문 목록 반복

            OrderHistDto orderHistDto = new OrderHistDto(order);
            List<OrderProduct> orderProducts = order.getOrderProducts() ;

            for (OrderProduct bean : orderProducts) {
                ProductImage productImage = productImageRepository.findByProductIdAndRepImageYesNo(bean.getProduct().getId(),"Y") ;

                OrderProductDto beanDto = new OrderProductDto(bean, productImage.getImageUrl()) ;

                orderHistDto.addOrderProductDto(beanDto);

            }

            orderHistDtos.add(orderHistDto);

        }

        return new PageImpl<OrderHistDto>(orderHistDtos, pageable, totalCount);

    }

    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(EntityNotFoundException::new) ;

        order.cancelOrder();
    }

    // 로그인 한 사람과 이메일의 주소가 동일한지 검사합니다.
    public boolean validateOrder(Long orderId, String email){
        Member curMember = memberRepository.findByEmail(email); // 로그인 한 사람

        Order order = orderRepository.findById(orderId).orElseThrow(EntityNotFoundException::new) ;

        Member savedMember = order.getMember() ; // 주문한 사람

        if (StringUtils.equals(curMember.getEmail(), savedMember.getEmail())) {
            return true ;
        } else {
            return false ;
        }
    }

    // 장바구니에서 주문할 상품 데이터를 전달 받아서 주문을 생성하는 로직을 구현합니다.
    public Long orders(List<OrderDto> orderDtoList, String email){
        // orderDtoList : 상품 아이디와 수량을 가지고 있는 객체들의 모음

        Member member = memberRepository.findByEmail(email) ;

        List<OrderProduct> orderProductList = new ArrayList<>();  // 주문할 상품 리스트

        for (OrderDto dto : orderDtoList) {
            Long productId = dto.getProductId() ;
            Product product = productRepository.findById(productId).orElseThrow(EntityNotFoundException::new) ;
            int count = dto.getCount() ;
            OrderProduct orderProduct = OrderProduct.createOrderProduct(product, count) ;
            orderProductList.add(orderProduct);

        }

        Order order = Order.createOrder(member, orderProductList) ;
        orderRepository.save(order) ;

        return order.getId() ;

    }
}
