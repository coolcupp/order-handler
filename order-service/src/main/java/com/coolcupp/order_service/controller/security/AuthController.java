package com.coolcupp.order_service.controller.security;

import com.coolcupp.order_service.dto.AuthDTO.AppUserRegisterRequestDTO;
import com.coolcupp.order_service.dto.AuthDTO.AppUserRegisterResponseDTO;
import com.coolcupp.order_service.service.AppUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {

    private final AppUserService appUserService;
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    public AuthController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @PostMapping("register")
    public ResponseEntity<AppUserRegisterResponseDTO> register(@RequestBody AppUserRegisterRequestDTO appUserRegisterDTO) {
        LOGGER.info("REGISTER USER: {} || request accepted", appUserRegisterDTO.getUsername());
        return appUserService.registerNewUser(appUserRegisterDTO);
    }

    // todo login controllerl

    // todo refresh controller

}
