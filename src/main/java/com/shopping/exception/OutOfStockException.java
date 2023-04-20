package com.shopping.exception;

// 재고 수량이 없을 때, 예외 발생
public class OutOfStockException extends RuntimeException{
    public OutOfStockException(String message) {
        super(message);
    }
}
