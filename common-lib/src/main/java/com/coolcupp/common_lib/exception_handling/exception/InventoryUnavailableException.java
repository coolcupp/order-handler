package com.coolcupp.common_lib.exception_handling.exception;

public class InventoryUnavailableException extends OrderHandlerException {
    public InventoryUnavailableException(String message) {
        super(message);
    }
}
