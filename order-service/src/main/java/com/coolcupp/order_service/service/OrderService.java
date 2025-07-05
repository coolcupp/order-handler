package com.coolcupp.order_service.service;

import com.coolcupp.order_service.dto.OrderRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {
    ResponseEntity<?> createNewOrder(OrderRequestDTO orderRequestDTO);
}
