package com.coolcupp.order_service.security.securityDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUserRegisterRequestDTO {
    private String username;
    private String password;
    private String email;
}
