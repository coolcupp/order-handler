package com.coolcupp.order_service.security.service;

import com.coolcupp.order_service.model.AppUser;
import com.coolcupp.order_service.repository.AppUserRepository;
import com.coolcupp.order_service.security.model.RefreshToken;
import com.coolcupp.order_service.security.repository.RefreshTokenRepository;
import com.coolcupp.order_service.security.securityDTO.RefreshTokenRequestDTO;
import com.coolcupp.order_service.security.securityDTO.RefreshTokenResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private AppUserRepository appUserRepository;
    private final JwtService jwtService;
    private static final Logger LOGGER = LoggerFactory.getLogger(RefreshTokenService.class);

    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository,
                               AppUserRepository appUserRepository, JwtService jwtService) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.appUserRepository = appUserRepository;
        this.jwtService = jwtService;
    }

    public RefreshToken createRefreshToken(String username) {
        AppUser appUser = appUserRepository.findAppUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        Long userId = appUser.getId();
        LOGGER.info("START CREATING REFRESH token for user: {}", userId);

        // if token for this user exists
        if (refreshTokenRepository.existsByUserId(userId)) {
            LOGGER.info("User with id: {} already exists, NEED TO DELETE token first", userId);
            refreshTokenRepository.deleteByUserId(userId);
            LOGGER.info("Token for user with id: {} DELETED SUCCESSFULLY", userId);
        }

        // generating token for app user
        LOGGER.info("GENERATING TOKEN for user: {}", userId);
        RefreshToken refreshToken = new RefreshToken(
                UUID.randomUUID().toString(),
                Instant.now().plusMillis(3600000), // 10 min
                appUser
        );
        LOGGER.info("TOKEN: {}", refreshToken.getToken());

        // saving token to DB
        refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }

    public ResponseEntity<RefreshTokenResponseDTO> refreshToken(RefreshTokenRequestDTO refreshToken) {
        Optional<RefreshToken> refreshTokenFromDB = refreshTokenRepository
                .findByToken(refreshToken.getRefreshToken());

        // if token not in DB or token EXPIRES
        if (refreshTokenFromDB.isEmpty() || isExpires(refreshTokenFromDB.get())) {
            // when logging in, the refresh token is generated again and deleted from the database
            // There is no need to delete refresh token from DB here
            // todo throw custom exception
            throw new RuntimeException("Refresh token expired or not found. Please make a new sign in request");
        }

        AppUser appUser = refreshTokenFromDB.get().getAppUser();

        // generating new JWT
        String newJWT = jwtService.generateToken(appUser);
        // generating new refresh token
        RefreshToken newRefreshToken = createRefreshToken(appUser.getUsername());

        return new ResponseEntity<>(new RefreshTokenResponseDTO(newJWT, newRefreshToken.getToken()), HttpStatus.OK);
    }


    public boolean isExpires(RefreshToken refreshToken) {
        return refreshToken.getExpiresAt().compareTo(Instant.now()) < 0;
    }
}
