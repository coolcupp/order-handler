package com.coolcupp.notification_service.handler;

import com.coolcupp.common_lib.event.KafkaCreateOrderEvent;
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
    public void handle(KafkaCreateOrderEvent kafkaCreateOrderEvent) {
        System.out.println("==========ACCEPTED FROM KAFKA================");
        System.out.println(kafkaCreateOrderEvent);
        // todo SAVE ORDER TO DB


    }

}
