package com.coolcupp.order_service.security.controller;

import com.coolcupp.order_service.security.authDTO.AppUserLoginRequestDTO;
import com.coolcupp.order_service.security.authDTO.AppUserRegisterRequestDTO;
import com.coolcupp.order_service.security.authDTO.AppUserRegisterResponseDTO;
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

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody AppUserLoginRequestDTO appUserLoginRequestDTO) {
        LOGGER.info("LOGIN USER: {} || request accepted", appUserLoginRequestDTO.getUsername());
        return appUserService.verify(appUserLoginRequestDTO);
    }

    // todo login controllerl

    // todo refresh controller

}
