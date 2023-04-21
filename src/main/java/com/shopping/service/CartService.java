package com.shopping.service;

import com.shopping.dto.CartProductDto;
import com.shopping.entity.Cart;
import com.shopping.entity.CartProduct;
import com.shopping.entity.Member;
import com.shopping.entity.Product;
import com.shopping.repository.CartProductRepository;
import com.shopping.repository.CartRepository;
import com.shopping.repository.MemberRepository;
import com.shopping.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class CartService {

    private final ProductRepository productRepository ;
    private final MemberRepository memberRepository ;
    private final CartRepository cartRepository ;
    private final CartProductRepository cartProductRepository ;

    public Long addCart(CartProductDto cartProductDto, String email) {

        Member member = memberRepository.findByEmail(email) ;
        Long memberId = member.getId() ;

        Cart cart = cartRepository.findByMemberId(memberId) ;
        if (cart==null){  // 카트가 없으면
            cart = Cart.createCart(member) ;
            cartRepository.save(cart) ;
        }

        Long productId = cartProductDto.getProductId() ;
        Product product = productRepository.findById(productId).orElseThrow(EntityNotFoundException::new) ;

        Long cartId = cart.getId() ;
        CartProduct savedCartProduct = cartProductRepository.findByCartIdAndProductsId(cartId, productId) ;

        int count = cartProductDto.getCount() ; // 담을 수량

        if (savedCartProduct != null) { // 카트에 이미 상품이 들어 있는 경우
            savedCartProduct.addCount(count); // 상품 수량 누적


        } else { // 해당 상품 처음 담는 경우
            savedCartProduct = CartProduct.createCartProduct(cart, product, count) ;
        }

        cartProductRepository.save(savedCartProduct) ;
        return savedCartProduct.getId();

    }
}
