package com.coolcupp.order_service.security.model;

import com.coolcupp.order_service.model.AppUser;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "refresh_token")
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

    public RefreshToken() {
    }

    public RefreshToken(String token, Instant expiresAt, AppUser appUser) {
        this.token = token;
        this.expiresAt = expiresAt;
        this.appUser = appUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Instant getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Instant expiresAt) {
        this.expiresAt = expiresAt;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RefreshToken that = (RefreshToken) o;
        return Objects.equals(id, that.id) && Objects.equals(token, that.token) && Objects.equals(expiresAt, that.expiresAt) && Objects.equals(appUser, that.appUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, token, expiresAt, appUser);
    }

    @Override
    public String toString() {
        return "RefreshToken{" +
                "id=" + id +
                ", token='" + token + '\'' +
                ", expiresAt=" + expiresAt +
                ", appUser=" + appUser +
                '}';
    }
}
