package com.coolcupp.notification_service.service;

import com.coolcupp.common_lib.event.CreateOrderEvent;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {

    ResponseEntity<?> getAllOrders();

    ResponseEntity<?> getOrdersByOrderId(Integer orderId);

    ResponseEntity<?> getOrdersByUserId(Integer userId);

    String createNewOrder(CreateOrderEvent createOrderEvent);
}
