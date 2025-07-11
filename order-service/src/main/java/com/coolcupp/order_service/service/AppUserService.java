package com.coolcupp.order_service.service;

import com.coolcupp.order_service.dto.AppUserDTO.AppUserRequestDTO;
import com.coolcupp.order_service.dto.AppUserDTO.AppUserResponseDTO;
import com.coolcupp.order_service.dto.AuthDTO.AppUserRegisterRequestDTO;
import com.coolcupp.order_service.dto.AuthDTO.AppUserRegisterResponseDTO;
import com.coolcupp.order_service.model.AppUser;
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

    ResponseEntity<AppUserRegisterResponseDTO> registerNewUser(AppUserRegisterRequestDTO appUserRegisterRequestDTO);
}
