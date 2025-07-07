package com.coolcupp.notification_service.service;

import com.coolcupp.common_lib.event.CreateOrderEvent;
import com.coolcupp.common_lib.event.OrderItemEvent;
import com.coolcupp.notification_service.mapper.OrderMapper;
import com.coolcupp.notification_service.model.Order;
import com.coolcupp.notification_service.repository.OrderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    public ResponseEntity<?> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        if (orders.isEmpty()) {
            return new ResponseEntity<>("Orders not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(orderMapper.toOrderResponseDTOList(orders), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getOrdersByOrderId(Integer orderId) {
        List<Order> orders = orderRepository.findByOrderId(orderId);
        if (orders.isEmpty()) {
            return new ResponseEntity<>("Orders not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(orderMapper.toOrderResponseDTOList(orders), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getOrdersByUserId(Integer userId) {
        List<Order> orders = orderRepository.findByUserId(userId);
        if (orders.isEmpty()) {
            return new ResponseEntity<>("Orders not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(orderMapper.toOrderResponseDTOList(orders), HttpStatus.OK);
    }

    @Override
    public String createNewOrder(CreateOrderEvent createOrderEvent) {
        List<OrderItemEvent> orderItems = createOrderEvent.getOrderItems();
        Integer orderId = createOrderEvent.getOrderId();
        Integer userId = createOrderEvent.getUserId();

        for (OrderItemEvent orderItem : orderItems) {
            Order order = new Order();
            order.setOrderId(orderId);
            order.setProductId(orderItem.getProductId());
            order.setUserId(userId);
            order.setQuantity(orderItem.getQuantityToOrder());
            order.setPrice(orderItem.getProductPrice());
            order.setDiscountPercent(orderItem.getDiscountPercent());
            order.setTotalPrice(orderItem.getTotalPrice());
            orderRepository.save(order);
        }
        return "SAVED SUCCESSFULLY";
    }
}
