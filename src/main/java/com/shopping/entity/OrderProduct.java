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
    @JoinColumn(name = "product_id")
    private Product product ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order ;

    private int orderPrice ; // 주문 가격
    private int count ; // 수량
    // private LocalDateTime regDate ; // 작성 일자
    // private LocalDateTime updateDate ; // 수정 일자

}
