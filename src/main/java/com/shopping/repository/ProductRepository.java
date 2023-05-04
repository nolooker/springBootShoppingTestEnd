package com.shopping.repository;

import com.shopping.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>, QuerydslPredicateExecutor<Product>, ProductRepositoryCustom {
    // 쿼리 메소드 작성
    // 상품의 이름으로 조회하여 목록을 반환받기
    List<Product> findProductByName(String name);

    // 특정 가격 이하의 데이터만 조회
    List<Product> findByPriceLessThan(Integer price);

    // 특정 가격 이하의 데이터를 조회하되, 가격에 대하여 내림차순 정렬
    List<Product> findByPriceLessThanOrderByPriceDesc(Integer price);

    @Query("select i from Product i where i.description like " +
          "%:description% order by i.price desc " )
    List<Product> findByProductDetail01(@Param("description") String description);

    @Query(value = "select * from Products i where i.description like " +
            "%:description% order by i.price desc ", nativeQuery = true )
    List<Product> findByProductDetail02(@Param("description") String description);
}
