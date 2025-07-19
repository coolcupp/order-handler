package com.coolcupp.notification_service.controller;

import com.coolcupp.notification_service.dto.OrderItemResponseDTO;
import com.coolcupp.notification_service.dto.OrderResponseDTO;
import com.coolcupp.notification_service.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("api")
@Slf4j
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("orders/all")
    public ResponseEntity<List<OrderResponseDTO>> getAllOrders() {
        log.info("REQUEST ACCEPTED: get all orders");
        return orderService.getAllOrders();
    }

    @GetMapping("orders/{orderId}")
    public ResponseEntity<List<OrderItemResponseDTO>> getOrderItemsByOrderId(@PathVariable("orderId") UUID orderId) {
        log.info("REQUEST ACCEPTED: get order items by order id: {}", orderId);
        return orderService.getOrderItemsByOrderId(orderId);
    }

    @GetMapping("orders/user/{userId}")
    public ResponseEntity<List<OrderItemResponseDTO>> getOrderItemsByUserId(@PathVariable("userId") Long userId) {
        log.info("REQUEST ACCEPTED: get order items by user id: {}", userId);
        return orderService.getOrderItemsByUserId(userId);
    }

}
