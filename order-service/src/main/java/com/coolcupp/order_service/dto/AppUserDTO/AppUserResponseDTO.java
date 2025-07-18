package com.coolcupp.order_service.dto.AppUserDTO;

import com.coolcupp.order_service.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUserResponseDTO {

    private Long userId;
    private String username;
    private String email;
    private Role role;

//    public AppUserResponseDTO() {
//    }
//
//    public AppUserResponseDTO(Long userId, String username, String email, Role role) {
//        this.userId = userId;
//        this.username = username;
//        this.email = email;
//        this.role = role;
//    }
//
//    public Long getUserId() {
//        return userId;
//    }
//
//    public void setUserId(Long userId) {
//        this.userId = userId;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public Role getRole() {
//        return role;
//    }
//
//    public void setRole(Role role) {
//        this.role = role;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        AppUserResponseDTO that = (AppUserResponseDTO) o;
//        return Objects.equals(userId, that.userId) && Objects.equals(username, that.username) && Objects.equals(email, that.email) && role == that.role;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(userId, username, email, role);
//    }
//
//    @Override
//    public String toString() {
//        return "AppUserResponseDTO{" +
//                "userId=" + userId +
//                ", username='" + username + '\'' +
//                ", email='" + email + '\'' +
//                ", role=" + role +
//                '}';
//    }
}
