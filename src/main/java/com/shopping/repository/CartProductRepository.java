package com.shopping.repository;

import com.shopping.dto.CartDetailDto;
import com.shopping.entity.CartProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartProductRepository extends JpaRepository<CartProduct, Long> {

    CartProduct findByCartIdAndProductsId(Long cartId, Long productId) ;

    // Long cartProductId, String name, int price, int count, String imageUrl
    @Query(" select new com.shopping.dto.CartDetailDto(cp.id, i.name, i.price, cp.count , pi.imageUrl)" +
            " from CartProduct cp, ProductImage pi " +
            " join cp.products i " +
            " where cp.cart.id = :cartId " +
            " and pi.product.id = cp.products.id " +
            " and pi.repImageYesNo = 'Y' " +
            " order by cp.regDate desc ")

    List<CartDetailDto> findCartDetailDtoList(Long cartId) ;
}
