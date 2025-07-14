package com.coolcupp.order_service.security.securityDTO;

import java.util.Objects;

public class AppUserRegisterResponseDTO {

    private Long userId;

    private String username;

    private String email;

    public AppUserRegisterResponseDTO() {
    }

    public AppUserRegisterResponseDTO(Long userId, String username, String email) {
        this.userId = userId;
        this.username = username;
        this.email = email;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppUserRegisterResponseDTO that = (AppUserRegisterResponseDTO) o;
        return Objects.equals(userId, that.userId) && Objects.equals(username, that.username) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, username, email);
    }

    @Override
    public String toString() {
        return "AppUserRegisterResponseDTO{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
