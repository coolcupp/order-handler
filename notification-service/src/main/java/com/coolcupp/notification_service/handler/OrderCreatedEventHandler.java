package com.coolcupp.notification_service.handler;

import com.coolcupp.common_lib.event.KafkaCreateOrderEvent;
import com.coolcupp.notification_service.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(topics = "ordersTopic")
public class OrderCreatedEventHandler {

    private final OrderService orderService;
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderCreatedEventHandler.class);

    public OrderCreatedEventHandler(OrderService orderService) {
        this.orderService = orderService;
    }

    @KafkaHandler
    public void handle(KafkaCreateOrderEvent kafkaCreateOrderEvent) {
        LOGGER.info("Received KafkaCreateOrderEvent for ORDER ID: {}", kafkaCreateOrderEvent.getOrderId());
        orderService.createNewOrder(kafkaCreateOrderEvent);
        LOGGER.info("Order created for ORDER ID: {}", kafkaCreateOrderEvent.getOrderId());
    }

}
