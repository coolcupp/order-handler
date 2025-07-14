package com.coolcupp.order_service.security.securityDTO;

import com.coolcupp.order_service.security.model.RefreshToken;

import java.util.Objects;

public class RefreshTokenResponseDTO {
    private String newJWT;

    private String newRefreshToken;

    public RefreshTokenResponseDTO() {
    }

    public RefreshTokenResponseDTO(String newJWT, String newRefreshToken) {
        this.newJWT = newJWT;
        this.newRefreshToken = newRefreshToken;
    }

    public String getNewJWT() {
        return newJWT;
    }

    public void setNewJWT(String newJWT) {
        this.newJWT = newJWT;
    }

    public String getNewRefreshToken() {
        return newRefreshToken;
    }

    public void setNewRefreshToken(String newRefreshToken) {
        this.newRefreshToken = newRefreshToken;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RefreshTokenResponseDTO that = (RefreshTokenResponseDTO) o;
        return Objects.equals(newJWT, that.newJWT) && Objects.equals(newRefreshToken, that.newRefreshToken);
    }

    @Override
    public int hashCode() {
        return Objects.hash(newJWT, newRefreshToken);
    }

    @Override
    public String toString() {
        return "RefreshTokenResponseDTO{" +
                "newJWT='" + newJWT + '\'' +
                ", newRefreshToken='" + newRefreshToken + '\'' +
                '}';
    }
}
