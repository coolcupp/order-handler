package com.coolcupp.order_service.security.service;

import com.coolcupp.common_lib.exception_handling.exception.InvalidRefreshTokenException;
import com.coolcupp.order_service.model.AppUser;
import com.coolcupp.order_service.repository.AppUserRepository;
import com.coolcupp.order_service.security.model.RefreshToken;
import com.coolcupp.order_service.security.repository.RefreshTokenRepository;
import com.coolcupp.order_service.security.securityDTO.RefreshTokenRequestDTO;
import com.coolcupp.order_service.security.securityDTO.RefreshTokenResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private AppUserRepository appUserRepository;
    private final JwtService jwtService;

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
        log.info("START CREATING REFRESH token for user: {}", userId);

        // if token for this user exists
        if (refreshTokenRepository.existsByUserId(userId)) {
            log.warn("User with id: {} already exists, NEED TO DELETE token first", userId);
            refreshTokenRepository.deleteByUserId(userId);
            log.info("Token for user with id: {} DELETED SUCCESSFULLY", userId);
        }

        // generating token for app user
        log.info("GENERATING TOKEN for user: {}", userId);
        RefreshToken refreshToken = new RefreshToken(
                UUID.randomUUID().toString(),
                Instant.now().plusMillis(3600000), // 10 min
                appUser
        );

        log.info("Refresh token for user created and saved to DB");
        // saving token to DB
        refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }

    public ResponseEntity<RefreshTokenResponseDTO> refreshToken(RefreshTokenRequestDTO refreshToken) {
        log.info("Start refreshing token...");
        Optional<RefreshToken> refreshTokenFromDB = refreshTokenRepository
                .findByToken(refreshToken.getRefreshToken());

        // if token not in DB or token EXPIRES
        if (refreshTokenFromDB.isEmpty() || isExpires(refreshTokenFromDB.get())) {
            log.info("Refresh token not found in DB or refresh token expired");
            // when logging in, the refresh token is generated again and deleted from the database
            // There is no need to delete refresh token from DB here
            throw new InvalidRefreshTokenException(
                    "Refresh token expired or not found. Please make a new sign in request");
        }

        AppUser appUser = refreshTokenFromDB.get().getAppUser();

        // generating new JWT
        log.info("Start generating new JWT and new Refresh for user: {}", appUser.getId());
        String newJWT = jwtService.generateToken(appUser);
        // generating new refresh token
        RefreshToken newRefreshToken = createRefreshToken(appUser.getUsername());

        return new ResponseEntity<>(new RefreshTokenResponseDTO(newJWT, newRefreshToken.getToken()), HttpStatus.OK);
    }


    public boolean isExpires(RefreshToken refreshToken) {
        return refreshToken.getExpiresAt().compareTo(Instant.now()) < 0;
    }
}
