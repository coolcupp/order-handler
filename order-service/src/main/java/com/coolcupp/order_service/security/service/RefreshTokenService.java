package com.coolcupp.order_service.security.service;

import com.coolcupp.order_service.model.AppUser;
import com.coolcupp.order_service.repository.AppUserRepository;
import com.coolcupp.order_service.security.model.RefreshToken;
import com.coolcupp.order_service.security.repository.RefreshTokenRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private AppUserRepository appUserRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(RefreshTokenService.class);

    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository,
                               AppUserRepository appUserRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.appUserRepository = appUserRepository;
    }

    @Transactional
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
                Instant.now().plusMillis(600000), // 10 min
                appUser
        );
        LOGGER.info("TOKEN: {}", refreshToken.getToken());

        // saving token to DB
        refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }
}
