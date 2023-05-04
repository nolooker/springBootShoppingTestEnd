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
    @JoinColumn(name = "products_id")
    private Product products ;

    public static CartProduct createCartProduct(Cart cart, Product product, int count){
        CartProduct cartProduct = new CartProduct();
        cartProduct.setCart(cart);
        cartProduct.setProducts(product);
        cartProduct.setCount(count);

        return cartProduct ;
    }

    public void addCount(int count){
        this.count += count ;
    }

    public void updateCount(int count){
        this.count = count ;
    }

}
