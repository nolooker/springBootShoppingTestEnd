package com.shopping.controller;

import com.shopping.dto.OrderDto;
import com.shopping.dto.OrderHistDto;
import com.shopping.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService ;

    @PostMapping(value = "/order")
    public @ResponseBody ResponseEntity order(@RequestBody @Valid OrderDto orderDto, BindingResult error, Principal principal) {

            if (error.hasErrors()){
                StringBuilder sb = new StringBuilder();
                List<FieldError> fieldErrors = error.getFieldErrors() ;

                for (FieldError ferr : fieldErrors) {
                    sb.append(ferr.getDefaultMessage()) ;
                }

                return new ResponseEntity<String>(sb.toString(), HttpStatus.BAD_REQUEST) ;
            }

            String email = principal.getName();
            Long orderId = 0L ;

            try {
                orderId = orderService.order(orderDto, email) ;

            }catch (Exception err) {
                err.printStackTrace();
                return new ResponseEntity<String>(err.getMessage(), HttpStatus.BAD_REQUEST) ;
            }

            return new ResponseEntity<Long>(orderId, HttpStatus.OK) ;

        }

        @GetMapping(value = {"/orders", "/orders/{page}"})
        public String orderHistory(@PathVariable("page") Optional<Integer> page, Principal principal, Model model) {
            Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0,2) ;

            Page<OrderHistDto> orderHistDtoPage = orderService.getOrderList(principal.getName(), pageable);

            model.addAttribute("orders", orderHistDtoPage) ;
            model.addAttribute("page", pageable.getPageNumber()) ;
            model.addAttribute("maxPage", 5) ;

            return "order/orderHist" ;

        }

        @PostMapping(value = "/order/{orderId}/cancel")
        public @ResponseBody ResponseEntity cancelOrder(@PathVariable("orderId") Long orderId, Principal principal) {

            String email = principal.getName();

            if (orderService.validateOrder(orderId, email)) {
                orderService.cancelOrder(orderId);
                return new ResponseEntity<Long>(orderId, HttpStatus.OK) ;

            }else {
                return new ResponseEntity<String>("주문 취소 권한이 없습니다.", HttpStatus.FORBIDDEN) ;
            }

        }
    }

