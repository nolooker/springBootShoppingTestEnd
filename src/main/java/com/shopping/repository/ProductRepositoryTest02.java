package com.shopping.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shopping.constant.ProductStatus;
import com.shopping.entity.Product;
import com.shopping.entity.QProduct;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class ProductRepositoryTest02 {
    @Autowired
    ProductRepository productRepository ;

    @Test
    @DisplayName("@Query 메소드를 사용한 상품 조회 01")
    public void findByProductDetail01(){
        List<Product> itemList = productRepository.findByProductDetail01("어");
        for(Product product : itemList){
            System.out.println(product.toString());
        }
    }

    @Test
    @DisplayName("@Query 메소드를 사용한 상품 조회 02")
    public void findByProductDetail02(){
        List<Product> itemList = productRepository.findByProductDetail02("아");
        for(Product product : itemList){
            System.out.println(product.toString());
        }
    }


    @Test
    @DisplayName("특정 가격 이하 상품들만 조회하되 가격이 높은 것 부터 조회")
    public void findByPriceLessThanOrderByPriceDescTest(){
        List<Product> itemList = productRepository.findByPriceLessThanOrderByPriceDesc(300) ;
        for(Product product : itemList){
            System.out.println(product.toString());
        }
    }

    @Test
    @DisplayName("특정 가격 이하 상품들만 조회")
    public void findByPriceLessThanTest(){
        List<Product> itemList = productRepository.findByPriceLessThan(300) ;
        for(Product product : itemList){
            System.out.println(product.toString());
        }
    }

    @Test
    @DisplayName("상품명으로 조회 테스트")
    public void findProductByNameTest(){
        List<Product> itemList = productRepository.findProductByName("오렌지") ;
        for(Product product : itemList){
            System.out.println(product.toString());
        }
    }


    @Test
    @DisplayName("상품 여러개 추가하기")
    public void createProductTestMany(){
        String[] fruit = {"사과", "배", "오렌지"};
        String[] description = {"달아요", "맛있어요", "맛없어요", "떫어요"};
        Integer[] stock = {100, 200, 300, 400};
        Integer[] price = {111, 222, 333, 444, 555};

        for (int i = 0; i < 10; i++) {
            Product product = new Product();

            product.setName(fruit[i % fruit.length]);
            product.setPrice(price[i % price.length]);
            product.setDescription(description[i % description.length]);
            product.setProductStatus(ProductStatus.SELL);
            product.setStock(stock[i % stock.length]);
            product.setRegDate(LocalDateTime.now());
            product.setUpdateDate(LocalDateTime.now()); ;

            Product savedItem = productRepository.save(product);
            System.out.println(savedItem.toString());
        }
    }

    @Test
    @DisplayName("데이터 생성(for QueryDSL)")
    public void createProductListNew(){
        String[] fruit = {"사과", "배", "오렌지"};
        String[] description = {"달아요", "맛있어요", "맛없어요", "떫어요"};
        Integer[] stock = {100, 200, 300, 400, 500, 600};
        Integer[] price = {111, 222, 333, 444, 555};

        for (int i = 0; i < 30; i++) {
            Product product = new Product();

            product.setName(fruit[i % fruit.length]);
            product.setPrice(price[i % price.length]);
            product.setDescription(description[i % description.length]);
            if(i%2 == 0){
                product.setProductStatus(ProductStatus.SELL);
            }else{
                product.setProductStatus(ProductStatus.SOLD_OUT);
            }
            product.setStock(stock[i % stock.length]);
            product.setRegDate(LocalDateTime.now());
            product.setUpdateDate(LocalDateTime.now()); ;

            Product savedItem = productRepository.save(product);
            System.out.println(savedItem.toString());
        }
    }

    // EntityManager를 주입해주는 어노테이션
    @PersistenceContext // JPA가 동작하는 영속성 작업 구간
    EntityManager em ; // 엔터티를 관리해주는 관리자

    @Test
    @DisplayName("query Dsl Test01")
    public void queryDslTest01(){
        // 현재 판매중(SELL)인 상품 중에서, 상품 설명에 '아'자가 들어 있는 상품들을 조회하여
        // 가격의 역순으로 출력합니다.
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QProduct qbean = QProduct.product ;

        JPAQuery<Product> query = queryFactory
                .selectFrom(qbean)
                .where(qbean.productStatus.eq(ProductStatus.SELL))
                .where(qbean.description.like("%" + "아" + "%"))
                .orderBy(qbean.price.desc());

        List<Product> itemList = query.fetch() ;

        for(Product product : itemList){
            System.out.println(product.toString());
        }
    }

    @Test
    @DisplayName("query Dsl Test2")
    public void queryDslTest02(){
        // 판매 중인 상품 중에서 단가가 200원 초과이고, 상품 설명에 '어'가 들어 있는 상품을 조회합니다.
        // 1페이지에 2개씩 볼건데, 2페이지를 조회할래요.
        BooleanBuilder booleanBuilder = new BooleanBuilder() ;
        QProduct product = QProduct.product ;

        int price = 200 ;
        String description = "어" ;

        booleanBuilder.and(product.description.like("%" + description + "%")) ;
        booleanBuilder.and(product.price.gt(price));
        booleanBuilder.and(product.productStatus.eq(ProductStatus.SELL));

        Pageable pageable = PageRequest.of(1, 2);
        Page<Product> result = productRepository.findAll(booleanBuilder, pageable) ;
        System.out.println("total elements : " + result.getTotalElements());

        List<Product> productList = result.getContent() ;
        for(Product item : productList){
            System.out.println(item.toString());
        }
    }
}






