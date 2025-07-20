package com.coolcupp.common_lib.exception_handling.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomExceptionResponseDTO {
    private Integer status;
    private LocalDateTime timestamp;
    private String message;
    private String path;
}
