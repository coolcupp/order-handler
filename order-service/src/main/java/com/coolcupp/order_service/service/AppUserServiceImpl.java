package com.coolcupp.order_service.service;

import com.coolcupp.order_service.dto.AppUserDTO.AppUserRequestDTO;
import com.coolcupp.order_service.dto.AppUserDTO.AppUserResponseDTO;
import com.coolcupp.order_service.model.AppUser;
import com.coolcupp.order_service.repository.AppUserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppUserServiceImpl implements AppUserService {

    private final AppUserRepository appUserRepository;

    public AppUserServiceImpl(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }


    @Override
    public ResponseEntity<List<AppUserResponseDTO>> getAllAppUsers() {
        List<AppUser> appUsers = appUserRepository.findAll();
        if (appUsers.isEmpty()) {
            System.out.println("Пизда, ничего нету");
            // todo throw custom exception
            // return new ResponseEntity<>("Users not found", HttpStatus.NO_CONTENT);
        }
        // list of app users -> list of app user dto's
        List<AppUserResponseDTO> appUserResponseDTOList = appUsers.stream()
                .map(appUser -> new AppUserResponseDTO(
                        appUser.getId(),
                        appUser.getUsername(),
                        appUser.getEmail(),
                        appUser.getRole()
                ))
                .toList();
        return new ResponseEntity<>(appUserResponseDTOList, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<AppUserResponseDTO> getAppUserById(Long id) {
        if (!appUserRepository.existsById(id)) {
            System.out.println("Пизда, ничего нету.");
            // todo throw custom exception
            // return new ResponseEntity<>("User with id " + id + " not found", HttpStatus.NOT_FOUND);
        }
        // app user -> appUserDTO
        AppUser appUser = appUserRepository.findById(id).get();
        AppUserResponseDTO appUserResponseDTO = new AppUserResponseDTO(
                appUser.getId(),
                appUser.getUsername(),
                appUser.getEmail(),
                appUser.getRole()
        );
        return new ResponseEntity<>(appUserResponseDTO, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<AppUserResponseDTO> createAppUser(AppUserRequestDTO appUserRequestDTO) {
        AppUser appUser = new AppUser(
                appUserRequestDTO.getUsername(),
                appUserRequestDTO.getPassword(),
                appUserRequestDTO.getEmail(),
                appUserRequestDTO.getRole()
        );
        appUserRepository.save(appUser);
        AppUserResponseDTO appUserResponseDTO = new AppUserResponseDTO(
                appUser.getId(),
                appUser.getUsername(),
                appUser.getEmail(),
                appUser.getRole()
        );
        return new ResponseEntity<>(appUserResponseDTO, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<AppUserResponseDTO> deleteAppUserById(Long id) {
        if (!appUserRepository.existsById(id)) {
            System.out.println("Пизда, ничего нету");
            // todo throw custom exception
            // return new ResponseEntity<>("User with id " + id + " not found", HttpStatus.NOT_FOUND);
        }
        AppUser appUser = appUserRepository.findById(id).get();
        appUserRepository.deleteById(id);
        AppUserResponseDTO appUserResponseDTO = new AppUserResponseDTO(
                appUser.getId(),
                appUser.getUsername(),
                appUser.getEmail(),
                appUser.getRole()
        );
        return new ResponseEntity<>(appUserResponseDTO, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<AppUserResponseDTO> updateAppUserById(Long id, AppUserRequestDTO appUserRequestDTO) {
        if (!appUserRepository.existsById(id)) {
            System.out.println("Пиздец, не найдено");
            // todo throw custom exception
            // return new ResponseEntity<>("User with id " + id + " not found", HttpStatus.NOT_FOUND);
        }
        AppUser appUserFromDb = appUserRepository.findById(id).get();
        appUserFromDb.setUsername(appUserRequestDTO.getUsername());
        appUserFromDb.setPassword(appUserRequestDTO.getPassword());
        appUserFromDb.setEmail(appUserRequestDTO.getEmail());
        appUserFromDb.setRole(appUserRequestDTO.getRole());

        appUserRepository.save(appUserFromDb);
        AppUserResponseDTO appUserResponseDTO = new AppUserResponseDTO(
                appUserFromDb.getId(),
                appUserFromDb.getUsername(),
                appUserFromDb.getEmail(),
                appUserFromDb.getRole()
        );
        return new ResponseEntity<>(appUserResponseDTO, HttpStatus.OK);
    }
}
