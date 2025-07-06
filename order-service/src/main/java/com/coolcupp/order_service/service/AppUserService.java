package com.coolcupp.order_service.service;

import com.coolcupp.order_service.dto.AppUserDTO.AppUserRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface AppUserService {
    ResponseEntity<?> getAllAppUsers();

    ResponseEntity<?> getAppUserById(Integer id);

    ResponseEntity<?> createAppUser(AppUserRequestDTO appUserRequestDTO);

    ResponseEntity<?> deleteAppUserById(Integer id);

    ResponseEntity<?> updateAppUserById(Integer id, AppUserRequestDTO appUserRequestDTO);

}
