package com.coolcupp.order_service.security.model;

import com.coolcupp.order_service.model.AppUser;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name = "refresh_token")
@NoArgsConstructor
@Data
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "id")
    private Long id;

    @Column(name = "token",nullable = false)
    private String token;

    @Column(name = "expires_at")
    private Instant expiresAt;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private AppUser appUser;

    public RefreshToken(String token, Instant expiresAt, AppUser appUser) {
        this.token = token;
        this.expiresAt = expiresAt;
        this.appUser = appUser;
    }
}
