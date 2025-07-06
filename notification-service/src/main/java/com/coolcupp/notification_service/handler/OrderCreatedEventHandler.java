package com.coolcupp.notification_service.handler;

import com.coolcupp.order_service.event.CreateOrderEvent;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(topics = "ordersTopic")
public class OrderCreatedEventHandler {

    @KafkaHandler
    public void handle(CreateOrderEvent createOrderEvent) {
        System.out.println(createOrderEvent);
    }

}
