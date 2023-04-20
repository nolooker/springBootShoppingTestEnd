package com.shopping.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "cart_products")
@Getter @Setter @ToString
public class CartProduct extends BaseEntity{

    @Id
    @Column(name = "cart_product_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;

    private int count ; // 구매 개수

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product products ;

}
