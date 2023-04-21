package com.shopping.entity;

import com.shopping.constant.OrderStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter @Setter @ToString
public class Order extends BaseEntity{ // 주문 Entity

    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Member member ;

    // mappedBy = "order"의 order는 OrderProduct 클래스에 들어 있는 변수 이름
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)

    private List<OrderProduct> orderProducts = new ArrayList<OrderProduct>() ;

    private LocalDateTime orderDate ; // 주문 일자

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus ; // 주문 상태

//    private LocalDateTime regDate ; // 작성 일자

//    private LocalDateTime updateDate ; // 수정 일자

    public void addOrderProduct(OrderProduct orderProduct){
        orderProducts.add(orderProduct) ;

        orderProduct.setOrder(this);
    }

    public static Order createOrder(Member member, List<OrderProduct> orderProductList) {

        Order order = new Order();
        order.setMember(member);

        for (OrderProduct bean : orderProductList){

            order.addOrderProduct(bean);

        }

        order.setOrderStatus(OrderStatus.ORDER);
        order.setOrderDate(LocalDateTime.now());

        return order ;
    }

    public int getTotalPrice() {
        int totalPrice = 0;

        for(OrderProduct bean : orderProducts) {
            totalPrice += bean.getTotalPrice() ;
        }

        return totalPrice ;
    }

    public void cancelOrder () {
        this.orderStatus = OrderStatus.CANCEL ;

        for (OrderProduct bean : orderProducts) {
            bean.cancel();
        }
    }

}
