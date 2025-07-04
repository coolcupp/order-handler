package com.coolcupp.notification_service.controller;

import com.coolcupp.notification_service.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("orders/all")
    public ResponseEntity<?> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("orders/{orderId}")
    public ResponseEntity<?> getOrdersByOrderId(@PathVariable("orderId") Integer orderId) {
        return orderService.getOrdersByOrderId(orderId);
    }

    @GetMapping("orders/user/{userId}")
    public ResponseEntity<?> getOrdersByUserId(@PathVariable("userId") Integer userId) {
        return orderService.getOrdersByUserId(userId);
    }

}
