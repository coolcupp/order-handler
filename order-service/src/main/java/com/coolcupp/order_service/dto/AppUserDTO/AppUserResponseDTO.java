package com.coolcupp.order_service.dto.AppUserDTO;

import java.util.Objects;

public class AppUserResponseDTO {

    private String username;
    private String email;
    private String role;

    public AppUserResponseDTO() {
    }

    public AppUserResponseDTO(String username, String email, String role) {
        this.username = username;
        this.email = email;
        this.role = role;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppUserResponseDTO that = (AppUserResponseDTO) o;
        return Objects.equals(username, that.username) && Objects.equals(email, that.email) && Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, email, role);
    }

    @Override
    public String toString() {
        return "AppUserResponseDTO{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
