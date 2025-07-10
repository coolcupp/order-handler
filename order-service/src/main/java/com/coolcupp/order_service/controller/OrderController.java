package com.coolcupp.order_service.controller;

import com.coolcupp.order_service.dto.OrderDTO.CreateOrderDTO;
import com.coolcupp.order_service.service.OrderServiceImpl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api")
public class OrderController {

    private final OrderServiceImpl orderService;

    public OrderController(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }

    @PostMapping("orders")
    public ResponseEntity<?> createNewOrder(@RequestBody CreateOrderDTO createOrderDTO) {

        // GETIING AND GENERATE ORDER ID AND USER ID
        // todo getting userId and set to createOrderDTO from serurity
        createOrderDTO.setUserId(123123L);
        createOrderDTO.setOrderId(UUID.randomUUID()); // generic random order UUID

        return orderService.createNewOrder(createOrderDTO);
    }

}
