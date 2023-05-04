package com.shopping.service;

import com.shopping.dto.CartDetailDto;
import com.shopping.dto.CartOrderDto;
import com.shopping.dto.CartProductDto;
import com.shopping.dto.OrderDto;
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
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductRepository productRepository ;
    private final MemberRepository memberRepository ;
    private final CartRepository cartRepository ;
    private final CartProductRepository cartProductRepository;

    public Long addCart(CartProductDto cartProductDto, String email){
        Member member = memberRepository.findByEmail(email) ;
        Long memberId = member.getId() ;

        Cart cart = cartRepository.findByMemberId(memberId) ;
        if(cart==null){ // 카트가 없으면
            cart = Cart.createCart(member) ;
            cartRepository.save(cart) ;
        }

        Long productId = cartProductDto.getProductId() ;
        Product product = productRepository.findById(productId)
                .orElseThrow(EntityNotFoundException::new);

        Long cartId = cart.getId() ;
        CartProduct savedCartProduct = cartProductRepository.findByCartIdAndProductsId(cartId, productId);

        int count = cartProductDto.getCount() ; // 담을 수량

        if(savedCartProduct != null){ // 카트에 이미 상품이 들어 있는 경우
            savedCartProduct.addCount(count); // 상품 수량 누적

        }else{ // 해당 상품 처음 담는 경우
            savedCartProduct = CartProduct.createCartProduct(cart, product, count);
        }
        cartProductRepository.save(savedCartProduct) ;
        return savedCartProduct.getId();
    }

    @Transactional(readOnly = true)
    public List<CartDetailDto> getCartList(String email){
        Member member = memberRepository.findByEmail(email) ;

        Long memberId = member.getId() ;
        Cart cart = cartRepository.findByMemberId(memberId) ;

        List<CartDetailDto> cartDetailDtoList = new ArrayList<>() ;

        if(cart == null){ // 방금 카트를 준비한 경우
            return cartDetailDtoList ; // return empty list

        }else{
            cartDetailDtoList = cartProductRepository.findCartDetailDtoList(cart.getId()) ;
            return cartDetailDtoList ;
        }
    }

    public void deleteCartProduct(Long cartProductId){
        CartProduct cartProduct = cartProductRepository.findById(cartProductId)
                .orElseThrow(EntityNotFoundException::new) ;
        cartProductRepository.delete(cartProduct);
    }

    @Transactional(readOnly = true) // 이 회원이 수정/삭제 권한이 있는 지 체크합니다.
    public boolean validateCartProduct(Long cartProductId, String email) {
        Member current = memberRepository.findByEmail(email) ; // 로그인한 사람
        CartProduct cartProduct = cartProductRepository.findById(cartProductId)
                .orElseThrow(EntityNotFoundException::new) ;
        Member saved = cartProduct.getCart().getMember() ; // 카트 소유자
        if(StringUtils.equals(current.getEmail(), saved.getEmail())){
            return true ;
        }else{
            return false ;
        }
    }

    // 이 상품(cartProductId)을 수량 count으로 변경할께요.
    public void updateCartProductCount(Long cartProductId, int count){
        CartProduct cartProduct = cartProductRepository.findById(cartProductId)
                .orElseThrow(EntityNotFoundException::new) ;

        cartProduct.updateCount(count);
        cartProductRepository.save(cartProduct);
    }

    private final OrderService orderService ;

    public Long orderCartProduct(List<CartOrderDto> cartOrderDtoList, String email){
        // 이번에 주문할 항목들
        List<OrderDto> orderDtoList = new ArrayList<>() ;

        for(CartOrderDto dto : cartOrderDtoList){
            Long productId = dto.getCartProductId() ;
            CartProduct cartProduct = cartProductRepository.findById(productId)
                    .orElseThrow(EntityNotFoundException::new) ;

            OrderDto orderDto = new OrderDto() ;
            orderDto.setProductId(cartProduct.getProducts().getId());
            orderDto.setCount(cartProduct.getCount());
            orderDtoList.add(orderDto) ;
        }

        // 장바구니에서 선택된 항목들에 대한 주문 로직 호출
        Long orderId = orderService.orders(orderDtoList, email) ;

        // 주문 신청한 목록들을 장바구니 목록에서 삭제해야 합니다.
        for(CartOrderDto dto : cartOrderDtoList){
            Long productId = dto.getCartProductId() ;
            CartProduct cartProduct = cartProductRepository.findById(productId)
                    .orElseThrow(EntityNotFoundException::new) ;

            cartProductRepository.delete(cartProduct);
        }

        return orderId ;
    }
}
