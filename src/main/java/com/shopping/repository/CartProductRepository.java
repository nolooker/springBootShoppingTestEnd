package com.shopping.repository;

import com.shopping.entity.CartProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartProductRepository extends JpaRepository<CartProduct, Long> {

    CartProduct findByCartIdAndProductsId(Long cartId, Long productId) ;

}
