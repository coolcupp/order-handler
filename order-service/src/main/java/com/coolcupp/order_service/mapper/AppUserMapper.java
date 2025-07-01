package com.coolcupp.order_service.mapper;

import com.coolcupp.order_service.model.AppUser;
import com.coolcupp.order_service.model.AppUserRequestDTO;
import com.coolcupp.order_service.model.AppUserResponseDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AppUserMapper {

    AppUserResponseDTO toResponseDTO(AppUser appUser);

    List<AppUserResponseDTO> toResponseDTOList(List<AppUser> appUserList);

    AppUser toEntity(AppUserRequestDTO appUserRequestDTO);
}
