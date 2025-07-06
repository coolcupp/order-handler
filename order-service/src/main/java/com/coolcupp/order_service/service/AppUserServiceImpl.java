package com.coolcupp.order_service.service;

import com.coolcupp.order_service.mapper.AppUserMapper;
import com.coolcupp.order_service.model.AppUser;
import com.coolcupp.order_service.dto.AppUserDTO.AppUserRequestDTO;
import com.coolcupp.order_service.repository.AppUserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppUserServiceImpl implements AppUserService {

    private final AppUserRepository appUserRepository;
    private final AppUserMapper appUserMapper;

    public AppUserServiceImpl(AppUserRepository appUserRepository, AppUserMapper appUserMapper) {
        this.appUserRepository = appUserRepository;
        this.appUserMapper = appUserMapper;
    }


    @Override
    public ResponseEntity<?> getAllAppUsers() {
        List<AppUser> appUsers = appUserRepository.findAll();
        if (appUsers.isEmpty()) {
            return new ResponseEntity<>("Users not found", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(appUserMapper.toResponseDTOList(appUsers), HttpStatus.OK);
    }


    @Override
    public ResponseEntity<?> getAppUserById(Integer id) {
        if (!appUserRepository.existsById(id)) {
            return new ResponseEntity<>("User with id " + id + " not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(appUserMapper.toResponseDTO(appUserRepository.findById(id).get()), HttpStatus.OK);
    }


    @Override
    public ResponseEntity<?> createAppUser(AppUserRequestDTO appUserRequestDTO) {
        AppUser appUser = appUserMapper.toEntity(appUserRequestDTO);
        appUserRepository.save(appUser);
        return new ResponseEntity<>(appUserMapper.toResponseDTO(appUser), HttpStatus.CREATED);
    }


    @Override
    public ResponseEntity<?> deleteAppUserById(Integer id) {
        if (!appUserRepository.existsById(id)) {
            return new ResponseEntity<>("User with id " + id + " not found", HttpStatus.NOT_FOUND);
        }
        appUserRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @Override
    public ResponseEntity<?> updateAppUserById(Integer id, AppUserRequestDTO appUserRequestDTO) {
        if (!appUserRepository.existsById(id)) {
            return new ResponseEntity<>("User with id " + id + " not found", HttpStatus.NOT_FOUND);
        }

        AppUser appUserFromDb = appUserRepository.findById(id).get();

        appUserFromDb.setId(id);
        appUserFromDb.setUsername(appUserRequestDTO.getUsername());
        appUserFromDb.setPassword(appUserRequestDTO.getPassword());
        appUserFromDb.setEmail(appUserRequestDTO.getEmail());
        appUserFromDb.setRole(appUserRequestDTO.getRole());

        appUserRepository.save(appUserFromDb);
        return new ResponseEntity<>(appUserMapper.toResponseDTO(appUserFromDb), HttpStatus.OK);
    }


}
