package com.coolcupp.common_lib.exception_handling.handler;

import com.coolcupp.common_lib.exception_handling.dto.CustomExceptionResponseDTO;
import com.coolcupp.common_lib.exception_handling.exception.InvalidRefreshTokenException;
import com.coolcupp.common_lib.exception_handling.exception.NotEnoughItemsInStorageException;
import com.coolcupp.common_lib.exception_handling.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
@Component
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<CustomExceptionResponseDTO> handleNotFoundException(NotFoundException exception,
                                                                              WebRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomExceptionResponseDTO(
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now(),
                exception.getMessage(),
                request.getDescription(false)
        ));
    }

    @ExceptionHandler(InvalidRefreshTokenException.class)
    public ResponseEntity<CustomExceptionResponseDTO> handleInvalidRefreshException(
            InvalidRefreshTokenException exception, WebRequest request) {

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new CustomExceptionResponseDTO(
                HttpStatus.UNAUTHORIZED.value(),
                LocalDateTime.now(),
                exception.getMessage(),
                request.getDescription(false)
        ));
    }

    @ExceptionHandler(NotEnoughItemsInStorageException.class)
    public ResponseEntity<CustomExceptionResponseDTO> handleNotEnoughItemsInStorageException(
            NotEnoughItemsInStorageException exception, WebRequest request) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomExceptionResponseDTO(
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now(),
                exception.getMessage(),
                request.getDescription(false)
        ));
    }

}
