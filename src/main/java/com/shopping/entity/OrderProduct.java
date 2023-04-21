package com.shopping.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class OrderProduct extends BaseEntity { // 주문 Entity

    @Id @GeneratedValue
    @Column(name = "order_product_id")
    private Long id ;

    // fetch = FetchType.LAZY 는 지연 로딩
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "products_id")
    private Product product ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order ;

    private int orderPrice ; // 주문 가격
    private int count ; // 수량
    // private LocalDateTime regDate ; // 작성 일자
    // private LocalDateTime updateDate ; // 수정 일자

    public static OrderProduct createOrderProduct(Product product, int count) {

        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setProduct(product);
        orderProduct.setCount(count);
        orderProduct.setOrderPrice(product.getPrice());

        product.removeStock(count);

        return orderProduct ;
    }

    public int getTotalPrice() {  // 해당 상품의 판매 금액
        return orderPrice * count ;
    }

    public void cancel(){ // 주문 취소시 재고 상품 다시 + 시키기
        this.getProduct().addStock(count);
    }
}
