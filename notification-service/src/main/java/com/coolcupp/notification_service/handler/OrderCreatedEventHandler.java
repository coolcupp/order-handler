package com.coolcupp.notification_service.handler;

import com.coolcupp.common_lib.event.CreateOrderEvent;
import com.coolcupp.notification_service.service.OrderService;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(topics = "ordersTopic")
public class OrderCreatedEventHandler {

    private final OrderService orderService;

    public OrderCreatedEventHandler(OrderService orderService) {
        this.orderService = orderService;
    }

    @KafkaHandler
    public void handle(CreateOrderEvent createOrderEvent) {
        System.out.println(createOrderEvent);
        orderService.createNewOrder(createOrderEvent);
    }

}
