package com.coolcupp.order_service.controller;

import com.coolcupp.order_service.dto.AppUserDTO.AppUserRequestDTO;
import com.coolcupp.order_service.dto.AppUserDTO.AppUserResponseDTO;
import com.coolcupp.order_service.service.AppUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/users")
public class AppUserController {

    private final AppUserService appUserService;

    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping("all")
    public ResponseEntity<List<AppUserResponseDTO>> getAllAppUsers() {
        log.info("REQUEST ACCEPTED: get all app users");
        return appUserService.getAllAppUsers();
    }

    @GetMapping("{id}")
    public ResponseEntity<AppUserResponseDTO> getAppUserById(@PathVariable("id") Long id) {
        log.info("REQUEST ACCEPTED: get app user by id: {}", id);
        return appUserService.getAppUserById(id);
    }

    @PostMapping
    public ResponseEntity<AppUserResponseDTO> createAppUser(@RequestBody AppUserRequestDTO appUserRequestDTO) {
        log.info("REQUEST ACCEPTED: create app user with username: {}", appUserRequestDTO.getUsername());
        return appUserService.createAppUser(appUserRequestDTO);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<AppUserResponseDTO> deleteAppUserById(@PathVariable("id") Long id) {
        log.info("REQUEST ACCEPTED: delete app user by id: {}", id);
        return appUserService.deleteAppUserById(id);
    }

    @PutMapping("{id}")
    public ResponseEntity<AppUserResponseDTO> updateAppUserById(@PathVariable("id") Long id,
                                                                @RequestBody AppUserRequestDTO appUserRequestDTO) {
        log.info("REQUEST ACCEPTED: update app user by id: {}", id);
        return appUserService.updateAppUserById(id, appUserRequestDTO);
    }
}
