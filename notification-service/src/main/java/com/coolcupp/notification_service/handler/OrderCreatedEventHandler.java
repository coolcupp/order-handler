package com.coolcupp.notification_service.handler;

import com.coolcupp.notification_service.event.CreateOrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(topics = "ordersTopic")
public class OrderCreatedEventHandler {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @KafkaHandler
    public void handle(CreateOrderEvent createOrderEvent) {
        LOGGER.info("Received CreateOrderEvent");
        System.out.println(createOrderEvent);
    }

}
