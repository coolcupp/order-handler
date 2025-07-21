package com.coolcupp.order_service.security.securityDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponseDTO {
    private String accessToken;
    private String refreshToken;
}
