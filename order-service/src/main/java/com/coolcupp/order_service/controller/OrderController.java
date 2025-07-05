package com.coolcupp.order_service.controller;

import com.coolcupp.order_service.dto.OrderRequestDTO;
import com.coolcupp.order_service.grpc.InventoryServiceGrpcClient;
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

//    // test GRPC method
//    @GetMapping("order/{productId}")
//    public ProductResponseGrpcDTO checkAvailability(@PathVariable("productId") Integer productId) {
//        return inventoryServiceGrpcClient.checkAvailability(productId);
//    }

    @PostMapping("orders")
    public ResponseEntity<?> createNewOrder(@RequestBody OrderRequestDTO orderRequestDTO) {
        return orderService.createNewOrder(orderRequestDTO);
    }

}
