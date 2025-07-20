package com.coolcupp.order_service.mapper;

import com.coolcupp.order_service.dto.AppUserDTO.AppUserRequestDTO;
import com.coolcupp.order_service.dto.AppUserDTO.AppUserResponseDTO;
import com.coolcupp.order_service.model.AppUser;
import com.coolcupp.order_service.model.Role;
import com.coolcupp.order_service.security.securityDTO.AppUserRegisterRequestDTO;
import com.coolcupp.order_service.security.securityDTO.AppUserRegisterResponseDTO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AppUserMapper {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AppUserMapper(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    // AppUser -> AppUserResponseDTO
    public AppUserResponseDTO toAppUserResponseDTOFromAppUser(AppUser appUser) {
        if (appUser == null) {
            return null;
        }
        AppUserResponseDTO appUserResponseDTO = new AppUserResponseDTO();

        appUserResponseDTO.setUserId(appUser.getId());
        appUserResponseDTO.setUsername(appUser.getUsername());
        appUserResponseDTO.setEmail(appUser.getEmail());
        appUserResponseDTO.setRole(appUser.getRole());

        return appUserResponseDTO;
    }


    // AppUserRequestDTO -> AppUser
    public AppUser toAppUserFromRequestDTO(AppUserRequestDTO appUserRequestDTO) {
        if (appUserRequestDTO == null) {
            return null;
        }
        AppUser appUser = new AppUser();

        appUser.setUsername(appUserRequestDTO.getUsername());
        appUser.setPassword(bCryptPasswordEncoder.encode(appUserRequestDTO.getPassword()));
        appUser.setEmail(appUserRequestDTO.getEmail());
        appUser.setRole(appUserRequestDTO.getRole());

        return appUser;
    }


    // AppUserRegisterRequestDTO -> AppUser
    public AppUser toAppUserFromRegisterRequestDTO(AppUserRegisterRequestDTO appUserRegisterRequestDTO) {
        if (appUserRegisterRequestDTO == null) {
            return null;
        }
        AppUser appUser = new AppUser();

        appUser.setUsername(appUserRegisterRequestDTO.getUsername());
        appUser.setPassword(bCryptPasswordEncoder.encode(appUserRegisterRequestDTO.getPassword()));
        appUser.setEmail(appUserRegisterRequestDTO.getEmail());
        appUser.setRole(Role.USER);

        return appUser;
    }


    // AppUser -> AppUserRegisterResponseDTO
    public AppUserRegisterResponseDTO toAppUserRegisterResponseDTOFromAppUser(AppUser appUser) {
        if (appUser == null) {
            return null;
        }
        AppUserRegisterResponseDTO appUserRegisterResponseDTO = new AppUserRegisterResponseDTO();

        appUserRegisterResponseDTO.setUserId(appUser.getId());
        appUserRegisterResponseDTO.setUsername(appUser.getUsername());
        appUserRegisterResponseDTO.setEmail(appUser.getEmail());

        return appUserRegisterResponseDTO;
    }


    // List<AppUser> -> List<AppUserResponseDTO>
    public List<AppUserResponseDTO> toAppUserResponseDTOListFromAppUserList(List<AppUser> appUserList) {
        if (appUserList == null) {
            return null;
        }

        return appUserList.stream()
                .map(appUser -> new AppUserResponseDTO(
                        appUser.getId(),
                        appUser.getUsername(),
                        appUser.getEmail(),
                        appUser.getRole()
                ))
                .toList();
    }

}
