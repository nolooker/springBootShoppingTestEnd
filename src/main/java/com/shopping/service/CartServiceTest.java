package com.shopping.service;

import com.shopping.constant.ProductStatus;
import com.shopping.constant.Role;
import com.shopping.dto.CartProductDto;
import com.shopping.entity.CartProduct;
import com.shopping.entity.Member;
import com.shopping.entity.Product;
import com.shopping.repository.CartProductRepository;
import com.shopping.repository.MemberRepository;
import com.shopping.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Random;

@SpringBootTest
@Transactional
public class CartServiceTest {
    @Autowired
    ProductRepository productRepository ;

    @Autowired
    MemberRepository memberRepository ;

    @Autowired
    CartProductRepository cartProductRepository ;

    @Autowired
    CartService cartService ;

    @Test
    @DisplayName("장바구니 담기 테스트")
    public void addCart(){
        Product product = createProduct();
        Member member = createMember();

        CartProductDto cartProductDto = new CartProductDto() ;
        cartProductDto.setCount(5);
        Long productId = product.getId() ;
        cartProductDto.setProductId(productId);

        String email = member.getEmail() ;
        Long cartProductId = cartService.addCart(cartProductDto, email);

        // 카트 상품 객체 구하기
        CartProduct cartProduct = cartProductRepository.findById(cartProductId)
                .orElseThrow(EntityNotFoundException::new);

        Assertions.assertEquals(product.getId(), cartProduct.getProducts().getId()); // 상품 아이디 동일한지 체크
        Assertions.assertEquals(cartProductDto.getCount(), cartProduct.getCount()); // 수량이 동일한지 체크
    }

    private Product createProduct() {
        Product product = new Product() ;
        product.setProductStatus(ProductStatus.SELL);
        //product.setId();
        product.setName("남성 패션");
        product.setStock(10);
        product.setPrice(10000);
        product.setDescription("넘 편해요");

        return productRepository.save(product) ;
    }
    private Member createMember() {
        Member member = new Member();

        String email = "test" + new Random().nextInt(1234567) + "@naver.com" ;
        member.setEmail(email);
        member.setAddress("영등포구 신길동");
        member.setRole(Role.USER);
        member.setPassword("1234");
        member.setName("김철수");

        return this.memberRepository.save(member) ;
    }
}
