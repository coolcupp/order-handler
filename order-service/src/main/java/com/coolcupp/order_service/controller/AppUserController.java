package com.coolcupp.order_service.controller;

import com.coolcupp.order_service.dto.AppUserDTO.AppUserRequestDTO;
import com.coolcupp.order_service.service.AppUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users")
public class AppUserController {

    private final AppUserService appUserService;

    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping("all")
    public ResponseEntity<?> getAllAppUsers() {
        return appUserService.getAllAppUsers();
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getAppUserById(@PathVariable("id") Integer id) {
        return appUserService.getAppUserById(id);
    }

    @PostMapping
    public ResponseEntity<?> createAppUser(@RequestBody AppUserRequestDTO appUserRequestDTO) {
        return appUserService.createAppUser(appUserRequestDTO);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteAppUserById(@PathVariable("id") Integer id) {
        return appUserService.deleteAppUserById(id);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateAppUserById(@PathVariable("id") Integer id, @RequestBody AppUserRequestDTO appUserRequestDTO) {
        return appUserService.updateAppUserById(id, appUserRequestDTO);
    }
}
