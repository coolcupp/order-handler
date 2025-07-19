package com.coolcupp.order_service.controller;

import com.coolcupp.order_service.dto.OrderDTO.CreateOrderDTO;
import com.coolcupp.order_service.model.AppUser;
import com.coolcupp.order_service.service.OrderServiceImpl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api")
@Slf4j
public class OrderController {

    private final OrderServiceImpl orderService;

    public OrderController(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }

    @PostMapping("orders")
    public ResponseEntity<?> createNewOrder(@RequestBody CreateOrderDTO createOrderDTO,
                                            @AuthenticationPrincipal AppUser appUser) {
        createOrderDTO.setUserId(appUser.getId());
        createOrderDTO.setOrderId(UUID.randomUUID()); // generic random order UUID

        log.info("REQUEST ACCEPTED: creating new order with ID: {}", createOrderDTO.getOrderId());
        return orderService.createNewOrder(createOrderDTO);
    }

}
