package com.coolcupp.order_service.security.controller;

import com.coolcupp.order_service.security.securityDTO.*;
import com.coolcupp.order_service.security.service.RefreshTokenService;
import com.coolcupp.order_service.service.AppUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@Slf4j
public class AuthController {

    private final AppUserService appUserService;
    private final RefreshTokenService refreshTokenService;

    public AuthController(AppUserService appUserService, RefreshTokenService refreshTokenService) {
        this.appUserService = appUserService;
        this.refreshTokenService = refreshTokenService;
    }

    @PostMapping("register")
    public ResponseEntity<AppUserRegisterResponseDTO> register(@RequestBody AppUserRegisterRequestDTO appUserRegisterDTO) {
        log.info("REGISTER USER: {} || request accepted", appUserRegisterDTO.getUsername());
        return appUserService.registerNewUser(appUserRegisterDTO);
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody AppUserLoginRequestDTO appUserLoginRequestDTO) {
        log.info("LOGIN USER: {} || request accepted", appUserLoginRequestDTO.getUsername());
        return appUserService.verify(appUserLoginRequestDTO);
    }

    @PostMapping("refresh")
    public ResponseEntity<RefreshTokenResponseDTO> refreshToken(@RequestBody RefreshTokenRequestDTO refreshTokenRequestDTO) {
        log.info("REFRESH TOKEN || request accepted");
        return refreshTokenService.refreshToken(refreshTokenRequestDTO);
    }




}
