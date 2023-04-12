package com.shopping.entity;

import com.shopping.constant.ProductStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
@Getter @Setter @ToString
public class Product {

    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id ;

    @Column(nullable = false, length = 50)
    private String name ;

    @Column(nullable = false, name = "price")
    private Integer price ;

    @Column(nullable = false)
    private Integer stock ;

    @Lob // CLOB, BLOB
    @Column(nullable = false)
    private String description ;

    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus ;

    private LocalDateTime regDate ;
    private LocalDateTime updateDate ;

}
