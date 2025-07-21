package com.coolcupp.notification_service.service;

import com.coolcupp.common_lib.event.KafkaCreateOrderEvent;
import com.coolcupp.common_lib.event.KafkaCreateOrderEventItem;
import com.coolcupp.common_lib.exception_handling.exception.NotFoundException;
import com.coolcupp.notification_service.dto.OrderItemResponseDTO;
import com.coolcupp.notification_service.dto.OrderResponseDTO;
import com.coolcupp.notification_service.mapper.OrderItemMapper;
import com.coolcupp.notification_service.mapper.OrderMapper;
import com.coolcupp.notification_service.model.Order;
import com.coolcupp.notification_service.model.OrderItem;
import com.coolcupp.notification_service.repository.OrderItemRepository;
import com.coolcupp.notification_service.repository.OrderRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderItemMapper orderItemMapper;
    private final OrderMapper orderMapper;

    public OrderServiceImpl(OrderRepository orderRepository, OrderItemRepository orderItemRepository,
                            OrderItemMapper orderItemMapper, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.orderItemMapper = orderItemMapper;
        this.orderMapper = orderMapper;
    }


    @Override
    public ResponseEntity<List<OrderResponseDTO>> getAllOrders() {
        List<Order> orders = orderRepository.findAll();

        // if not found orders
        if (orders.isEmpty()) {
            log.error("No orders found");
            throw new NotFoundException("No orders found");
        }

        // order -> order response dto
        List<OrderResponseDTO> orderResponseDTOList = orderMapper
                .toOrderResponseDTOListFromOrderList(orders);

        return new ResponseEntity<>(orderResponseDTOList, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<List<OrderItemResponseDTO>> getOrderItemsByOrderId(UUID orderId) {
        List<OrderItem> orderItems = orderItemRepository.findByOrderId(orderId);

        if (orderItems.isEmpty()) {
            log.error("No order items found for order with ID: {}", orderId);
            throw new NotFoundException("No order items found for order with ID: " + orderId);
        }

        // order item list -> order item response dto list
        List<OrderItemResponseDTO> orderItemResponseDTOList = orderItemMapper
                .toOrderItemResponseDTOListFromOrderItemList(orderItems);

        return new ResponseEntity<>(orderItemResponseDTOList, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<List<OrderItemResponseDTO>> getOrderItemsByUserId(Long userId) {
        List<OrderItem> orderItems = orderRepository.findByUserId(userId).stream()
                .flatMap(order -> orderItemRepository.findByOrderId(order.getId()).stream())
                .toList();

        if (orderItems.isEmpty()) {
            log.error("No order items found for user with ID: {}", userId);
            throw new NotFoundException("No order items found for user with ID: " + userId);
        }
        // List<OrderItem> -> List<OrderItemResponseDTO>
        List<OrderItemResponseDTO> orderItemResponseDTOList = orderItemMapper
                .toOrderItemResponseDTOListFromOrderItemList(orderItems);

        return new ResponseEntity<>(orderItemResponseDTOList, HttpStatus.OK);
    }


    @Override
    public void createNewOrder(KafkaCreateOrderEvent kafkaCreateOrderEvent) {
        log.info("Kafka message received for order with ID: {}", kafkaCreateOrderEvent.getOrderId());
        // PARSING EVENT
        UUID orderId = kafkaCreateOrderEvent.getOrderId();
        Long userId = kafkaCreateOrderEvent.getUserId();
        List<KafkaCreateOrderEventItem> kafkaCreateOrderEventItems = kafkaCreateOrderEvent.getEventItemList();

        // CALCULATING TOTAL ORDER PRICE
        BigDecimal totalOrderPrice = kafkaCreateOrderEventItems.stream()
                .map(KafkaCreateOrderEventItem::getTotalItemPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        log.info("Start creating and saving order to DB... ORDER ID: {}", kafkaCreateOrderEvent.getOrderId());
        // creating and saving order to db
        Order order = new Order(
                orderId,
                userId,
                totalOrderPrice
        );
        orderRepository.save(order);
        log.info("Order with ID: {} CREATED AND SAVED SUCCESSFULLY", orderId);

        // List<KafkaCreateOrderEventItems> -> List<OrderItem>
        List<OrderItem> orderItems = orderItemMapper.toOrderItemListFromKafkaEventItemList(order,
                kafkaCreateOrderEventItems
        );

        orderItemRepository.saveAll(orderItems);
        log.info("ORDER ID: {} || order items saved successfully", orderId);
    }
}
