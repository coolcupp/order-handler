package com.coolcupp.order_service.controller;

import com.coolcupp.order_service.dto.OrderDTO.CreateOrderDTO;
import com.coolcupp.order_service.service.OrderServiceImpl;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api")
public class OrderController {

    private final OrderServiceImpl orderService;
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    public OrderController(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }

    @PostMapping("orders")
    public ResponseEntity<?> createNewOrder(@RequestBody CreateOrderDTO createOrderDTO) {
        // GETIING AND GENERATE ORDER ID AND USER ID
        // todo getting userId and set to createOrderDTO from serurity
        createOrderDTO.setUserId(1L);
        createOrderDTO.setOrderId(UUID.randomUUID()); // generic random order UUID

        LOGGER.info("Order ID: {} || Accepted request to create an order", createOrderDTO.getOrderId());

        return orderService.createNewOrder(createOrderDTO);
    }

}
