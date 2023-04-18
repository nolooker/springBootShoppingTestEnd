package com.shopping.entity;

import com.shopping.constant.ProductStatus;
import com.shopping.dto.ProductFormDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "products")
@Getter @Setter @ToString
public class Product extends BaseEntity {

    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id ;

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

//    BaseTimeEntity 상속 받았기 때문에 주석처리
    // private LocalDateTime regDate ;
    // private LocalDateTime updateDate ;

    public void updateProduct(ProductFormDto productFormDto) {
        this.name = productFormDto.getName() ;
        this.price = productFormDto.getPrice() ;
        this.stock = productFormDto.getStock() ;
        this.description = productFormDto.getDescription() ;
        this.productStatus = productFormDto.getProductStatus() ;

    }

}
