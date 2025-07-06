package com.coolcupp.order_service.service;

import com.coolcupp.order_service.dto.OrderDTO.CreateOrderDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {
    ResponseEntity<?> createNewOrder(CreateOrderDTO orderRequestDTO);
}
