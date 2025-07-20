package com.coolcupp.common_lib.exception_handling.exception;


public class NotEnoughItemsInStorageException extends OrderHandlerException {
    public NotEnoughItemsInStorageException(String message) {
        super(message);
    }
}
