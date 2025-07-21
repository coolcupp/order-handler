package com.coolcupp.notification_service.handler;

import com.coolcupp.common_lib.event.KafkaCreateOrderEvent;
import com.coolcupp.notification_service.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(topics = "ordersTopic")
@Slf4j
public class OrderCreatedEventHandler {

    private final OrderService orderService;

    public OrderCreatedEventHandler(OrderService orderService) {
        this.orderService = orderService;
    }

    @KafkaHandler
    public void handle(KafkaCreateOrderEvent kafkaCreateOrderEvent) {
        log.info("Received KafkaCreateOrderEvent for ORDER ID: {}", kafkaCreateOrderEvent.getOrderId());
        orderService.createNewOrder(kafkaCreateOrderEvent);
        log.info("Order created for ORDER ID: {}", kafkaCreateOrderEvent.getOrderId());
    }

}
