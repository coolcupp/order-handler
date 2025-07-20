package com.coolcupp.common_lib.exception_handling.exception;

public abstract class OrderHandlerException extends RuntimeException {
    public OrderHandlerException(String message) {
        super(message);
    }
}
