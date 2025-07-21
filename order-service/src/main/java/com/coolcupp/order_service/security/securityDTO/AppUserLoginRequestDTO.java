package com.coolcupp.order_service.security.securityDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUserLoginRequestDTO {
    private String username;
    private String password;
}
