package com.coolcupp.notification_service.service;

import com.coolcupp.common_lib.event.CreateOrderEvent;
import com.coolcupp.notification_service.dto.OrderItemResponseDTO;
import com.coolcupp.notification_service.dto.OrderResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {

    ResponseEntity<List<OrderResponseDTO>> getAllOrders();

    ResponseEntity<List<OrderItemResponseDTO>> getOrderItemsByOrderId(Long orderId);

    ResponseEntity<List<OrderItemResponseDTO>> getOrderItemsByUserId(Long userId);

//    String createNewOrder(CreateOrderEvent createOrderEvent);
}
