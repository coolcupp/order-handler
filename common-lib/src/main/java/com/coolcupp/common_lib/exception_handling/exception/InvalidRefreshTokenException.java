package com.coolcupp.common_lib.exception_handling.exception;

public class InvalidRefreshTokenException extends OrderHandlerException {
    // expired or not found
    public InvalidRefreshTokenException(String message) {
        super(message);
    }
}
