package com.coolcupp.order_service.security.repository;

import com.coolcupp.order_service.security.model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM refresh_token WHERE user_id = :userId", nativeQuery = true)
    void deleteByUserId(Long userId);


    @Query(value = "SELECT EXISTS(SELECT 1 FROM refresh_token WHERE user_id = :userId)", nativeQuery = true)
    boolean existsByUserId(Long userId);

    Optional<RefreshToken> findByToken(String token);
}
