package com.coolcupp.order_service.service;

import com.coolcupp.order_service.dto.AppUserDTO.AppUserRequestDTO;
import com.coolcupp.order_service.dto.AppUserDTO.AppUserResponseDTO;
import com.coolcupp.order_service.security.securityDTO.AppUserLoginRequestDTO;
import com.coolcupp.order_service.security.securityDTO.AppUserRegisterRequestDTO;
import com.coolcupp.order_service.security.securityDTO.AppUserRegisterResponseDTO;
import com.coolcupp.order_service.security.securityDTO.JwtResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AppUserService {
    ResponseEntity<List<AppUserResponseDTO>> getAllAppUsers();

    ResponseEntity<AppUserResponseDTO> getAppUserById(Long id);

    ResponseEntity<AppUserResponseDTO> createAppUser(AppUserRequestDTO appUserRequestDTO);

    ResponseEntity<AppUserResponseDTO> deleteAppUserById(Long id);

    ResponseEntity<AppUserResponseDTO> updateAppUserById(Long id, AppUserRequestDTO appUserRequestDTO);

    ResponseEntity<AppUserRegisterResponseDTO> registerNewUser(
            AppUserRegisterRequestDTO appUserRegisterRequestDTO);

    ResponseEntity<JwtResponseDTO> verify(AppUserLoginRequestDTO appUserLoginRequestDTO);
}
