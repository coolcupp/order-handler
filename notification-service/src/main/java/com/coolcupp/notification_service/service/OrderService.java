package com.coolcupp.notification_service.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {

    ResponseEntity<?> getAllOrders();

    ResponseEntity<?> getOrdersByOrderId(Integer orderId);

    ResponseEntity<?> getOrdersByUserId(Integer userId);
}
