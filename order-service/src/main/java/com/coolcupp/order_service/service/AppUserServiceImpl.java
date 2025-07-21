package com.coolcupp.order_service.service;

import com.coolcupp.common_lib.exception_handling.exception.NotFoundException;
import com.coolcupp.order_service.dto.AppUserDTO.AppUserRequestDTO;
import com.coolcupp.order_service.dto.AppUserDTO.AppUserResponseDTO;
import com.coolcupp.order_service.mapper.AppUserMapper;
import com.coolcupp.order_service.security.securityDTO.AppUserLoginRequestDTO;
import com.coolcupp.order_service.security.securityDTO.AppUserRegisterRequestDTO;
import com.coolcupp.order_service.security.securityDTO.AppUserRegisterResponseDTO;
import com.coolcupp.order_service.model.AppUser;
import com.coolcupp.order_service.repository.AppUserRepository;
import com.coolcupp.order_service.security.model.RefreshToken;
import com.coolcupp.order_service.security.securityDTO.JwtResponseDTO;
import com.coolcupp.order_service.security.service.JwtService;
import com.coolcupp.order_service.security.service.RefreshTokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AppUserServiceImpl implements AppUserService {

    private final AppUserRepository appUserRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;
    private final AppUserMapper appUserMapper;

    public AppUserServiceImpl(AppUserRepository appUserRepository,
                              AuthenticationManager authenticationManager,
                              JwtService jwtService,
                              RefreshTokenService refreshTokenService,
                              AppUserMapper appUserMapper) {
        this.appUserRepository = appUserRepository;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.refreshTokenService = refreshTokenService;
        this.appUserMapper = appUserMapper;
    }


    @Override
    public ResponseEntity<List<AppUserResponseDTO>> getAllAppUsers() {
        List<AppUser> appUsers = appUserRepository.findAll();
        if (appUsers.isEmpty()) {
            log.info("No app users found in DB");
            throw new NotFoundException("No app users found in DB");
        }
        List<AppUserResponseDTO> appUserResponseDTOList =
                appUserMapper.toAppUserResponseDTOListFromAppUserList(appUsers);

        log.info("Some users found in DB");
        return new ResponseEntity<>(appUserResponseDTOList, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<AppUserResponseDTO> getAppUserById(Long id) {
        if (!appUserRepository.existsById(id)) {
            log.info("No app user found with id {}", id);
            throw new NotFoundException("No app user found with id: " + id);
        }
        // app user -> appUserDTO
        AppUser appUser = appUserRepository.findById(id).get();
        AppUserResponseDTO appUserResponseDTO = appUserMapper.toAppUserResponseDTOFromAppUser(appUser);
        log.info("User found with id {}", id);
        return new ResponseEntity<>(appUserResponseDTO, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<AppUserResponseDTO> createAppUser(AppUserRequestDTO appUserRequestDTO) {
        log.info("Start creating app user with username: {} ...", appUserRequestDTO.getUsername());

        // appUserRequestDTO -> appUser
        AppUser appUser = appUserMapper.toAppUserFromRequestDTO(appUserRequestDTO);
        // saving user to DB
        appUserRepository.save(appUser);
        // AppUser -> appUserResponseDTO
        AppUserResponseDTO appUserResponseDTO = appUserMapper.toAppUserResponseDTOFromAppUser(appUser);

        log.info("App user created with id {}", appUser.getId());
        return new ResponseEntity<>(appUserResponseDTO, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<AppUserResponseDTO> deleteAppUserById(Long id) {
        if (!appUserRepository.existsById(id)) {
            log.info("User with id:{} not found", id);
            throw new NotFoundException("User with id: " + id);
        }
        AppUser appUser = appUserRepository.findById(id).get();

        appUserRepository.deleteById(id);

        // appUser -> appUserResponseDTO
        AppUserResponseDTO appUserResponseDTO = appUserMapper.toAppUserResponseDTOFromAppUser(appUser);

        log.info("User with id:{} deleted", id);
        return new ResponseEntity<>(appUserResponseDTO, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<AppUserResponseDTO> updateAppUserById(Long id, AppUserRequestDTO appUserRequestDTO) {
        if (!appUserRepository.existsById(id)) {
            log.info("User with id:{} doesn't exists", id);
            throw new NotFoundException("User with ID: " + id + " doesn't exists");
        }
        AppUser appUserFromDb = appUserRepository.findById(id).get();

        // appUserRequestDTO -> appUser
        appUserFromDb = appUserMapper.toAppUserFromRequestDTO(appUserRequestDTO);

        // saving updated user to DB
        appUserRepository.save(appUserFromDb);

        // appUser -> appUserResponseDTO
        AppUserResponseDTO appUserResponseDTO = appUserMapper.toAppUserResponseDTOFromAppUser(appUserFromDb);

        log.info("User with id: {} updated", id);
        return new ResponseEntity<>(appUserResponseDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AppUserRegisterResponseDTO> registerNewUser(AppUserRegisterRequestDTO
                                                                                  appUserRegisterRequestDTO) {
        log.info("Start registration new user with username: {} ...", appUserRegisterRequestDTO.getUsername());

        AppUser appUser = appUserMapper.toAppUserFromRegisterRequestDTO(appUserRegisterRequestDTO);

        // saving user to DB
        appUserRepository.save(appUser);

        AppUserRegisterResponseDTO appUserRegisterResponseDTO =
                appUserMapper.toAppUserRegisterResponseDTOFromAppUser(appUser);

        log.info("User registered with id {}", appUser.getId());
        return new ResponseEntity<>(appUserRegisterResponseDTO, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<JwtResponseDTO> verify(AppUserLoginRequestDTO appUserLoginRequestDTO) {
        log.info("Start verifying user with username: {}", appUserLoginRequestDTO.getUsername());
        // load user from bd
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        appUserLoginRequestDTO.getUsername(), appUserLoginRequestDTO.getPassword())
        );

        if (authentication.isAuthenticated()) {
            log.info("User authenticated! Start generating token...");
            // getting user details from authenticate object
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            // generating jwt token
            String jwtToken = jwtService.generateToken(userDetails);
            // generating refresh token
            RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getUsername());

            JwtResponseDTO jwtResponseDTO = new JwtResponseDTO(
                    jwtToken,
                    refreshToken.getToken()
            );

            log.info("User with username: {} verifying completed", appUserLoginRequestDTO.getUsername());
            return new ResponseEntity<>(jwtResponseDTO, HttpStatus.OK);
        }

        log.error("User with username: {} not authenticated!", appUserLoginRequestDTO.getUsername());
        return null;
    }
}
