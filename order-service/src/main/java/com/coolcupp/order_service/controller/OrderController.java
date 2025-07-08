package com.coolcupp.order_service.controller;

import com.coolcupp.order_service.dto.OrderDTO.CreateOrderDTO;
import com.coolcupp.order_service.service.OrderServiceImpl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class OrderController {

    private final OrderServiceImpl orderService;

    public OrderController(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }

    @PostMapping("orders")
    public ResponseEntity<?> createNewOrder(@RequestBody CreateOrderDTO createOrderDTO) {
        return orderService.createNewOrder(createOrderDTO);
    }

}
