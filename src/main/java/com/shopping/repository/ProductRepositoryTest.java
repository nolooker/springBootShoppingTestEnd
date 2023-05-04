package com.shopping.repository;

import com.shopping.constant.ProductStatus;
import com.shopping.entity.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class ProductRepositoryTest {
    @Autowired
    ProductRepository productRepository ;

    @Test
    @DisplayName("상품 저장 테스트")
    public void createProductTest(){
        Product product = new Product();
        product.setProductStatus(ProductStatus.SELL);
        //product.setId();
        product.setDescription("사과는 참 맛있어요");
        product.setName("사과");
        product.setPrice(1000);
        product.setStock(100);
        product.setRegDate(LocalDateTime.now());
        product.setUpdateDate(LocalDateTime.now());

        Product savedItem = productRepository.save(product) ;
        System.out.println(savedItem.toString());
    }
}