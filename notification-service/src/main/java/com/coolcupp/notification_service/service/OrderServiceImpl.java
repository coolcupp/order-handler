package com.coolcupp.notification_service.service;

import com.coolcupp.notification_service.dto.OrderItemResponseDTO;
import com.coolcupp.notification_service.dto.OrderResponseDTO;
import com.coolcupp.notification_service.model.Order;
import com.coolcupp.notification_service.model.OrderItem;
import com.coolcupp.notification_service.repository.OrderItemRepository;
import com.coolcupp.notification_service.repository.OrderRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    public OrderServiceImpl(OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }


    @Override
    public ResponseEntity<List<OrderResponseDTO>> getAllOrders() {
        List<Order> orders = orderRepository.findAll();

        // if not found orders
        if (orders.isEmpty()) {
            // todo custom exception
            // return new ResponseEntity<>("Orders not found", HttpStatus.NOT_FOUND);
            System.out.println("Пиздец всё пустое");
        }

        // order -> order response dto todo mapstruct
        List<OrderResponseDTO> orderResponseDTOList = orders.stream()
                .map(order -> new OrderResponseDTO(
                        order.getId(),
                        order.getUserId(),
                        order.getTotalPrice(),
                        order.getOrderItems().stream()
                                .map(orderItem -> new OrderItemResponseDTO(
                                        orderItem.getProductId(),
                                        orderItem.getQuantity(),
                                        orderItem.getPrice(),
                                        orderItem.getDiscountPercent(),
                                        orderItem.getTotalItemPrice()
                                )).toList()
                ))
                .toList();

        return new ResponseEntity<>(orderResponseDTOList, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<List<OrderItemResponseDTO>> getOrderItemsByOrderId(Long orderId) {
        List<OrderItem> orderItems = orderItemRepository.findByOrderId(orderId);

        if (orderItems.isEmpty()) {
            // todo throw custom exception
            // return new ResponseEntity<>("Order items not found", HttpStatus.NOT_FOUND);
            System.out.println("пиздец всё пустое");
        }

        // order item list -> order item response dto list
        List<OrderItemResponseDTO> orderItemResponseDTOList = orderItems.stream()
                .map(orderItem -> new OrderItemResponseDTO(
                        orderItem.getProductId(),
                        orderItem.getQuantity(),
                        orderItem.getPrice(),
                        orderItem.getDiscountPercent(),
                        orderItem.getTotalItemPrice()
                ))
                .toList();

        return new ResponseEntity<>(orderItemResponseDTOList, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<List<OrderItemResponseDTO>> getOrderItemsByUserId(Long userId) {
        List<OrderItem> orderItems = orderRepository.findByUserId(userId).stream()
                .flatMap(order -> orderItemRepository.findByOrderId(order.getId()).stream())
                .toList();

        if (orderItems.isEmpty()) {
            // todo throw custom exception
            // return new ResponseEntity<>("Order items not found", HttpStatus.NOT_FOUND);
            System.out.println("Пиздец всё пустое");
        }

        List<OrderItemResponseDTO> orderItemResponseDTOList = orderItems.stream()
                .map(orderItem -> new OrderItemResponseDTO(
                        orderItem.getProductId(),
                        orderItem.getQuantity(),
                        orderItem.getPrice(),
                        orderItem.getDiscountPercent(),
                        orderItem.getTotalItemPrice()
                ))
                .toList();

        return new ResponseEntity<>(orderItemResponseDTOList, HttpStatus.OK);
    }
}
