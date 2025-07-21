package com.coolcupp.order_service.dto.AppUserDTO;

import com.coolcupp.order_service.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUserRequestDTO {
    private String username;
    private String password;
    private String email;
    private Role role;
}
