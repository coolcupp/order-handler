package com.coolcupp.order_service.service;

import com.coolcupp.common_lib.event.CreateOrderEvent;

import com.coolcupp.order_service.dto.OrderDTO.OrderItemDTO;
import com.coolcupp.order_service.dto.OrderDTO.CreateOrderDTO;

//import com.coolcupp.order_service.service.grpc.InventoryServiceGRPCClient;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    private final KafkaTemplate<String, CreateOrderEvent> kafkaTemplate;
//    private final InventoryServiceGRPCClient inventoryServiceGrpcClient;


    public OrderServiceImpl(KafkaTemplate<String, CreateOrderEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        // to grpc check availability
    }


    public ResponseEntity<?> createNewOrder(CreateOrderDTO createOrderDTO) {
        // parsing orderItemDto -> items, orderID, userID
        List<OrderItemDTO> orderItems = createOrderDTO.getOrderItems();
        UUID orderId = createOrderDTO.getOrderId();
        Long userId = createOrderDTO.getUserId();

        // todo logging request accepted

        // todo grpc check availability



//        for (OrderItemDTO orderItem : orderItems) {
//
//            // get info about product from grpc
//            ProductAvailabilityResponseDTO productResponseGrpcDTO =
//                    inventoryServiceGrpcClient.checkAvailability(orderItem.getProductId());
//
//            // check availability of product
//            if (orderItem.getQuantityToOrder() > productResponseGrpcDTO.getQuantity()) {
//                return new ResponseEntity<>("bad quantity of product: " + productResponseGrpcDTO.getName(),
//                        HttpStatus.NOT_ACCEPTABLE);
//            }
//
//            // creating a kafka message item and add in list
//            BigDecimal orderItemEventPrice = new BigDecimal(productResponseGrpcDTO.getPrice());
//            BigDecimal orderItemEventDiscountPercent = new BigDecimal(productResponseGrpcDTO.getDiscountPercent());
//            Integer quantityToOrder = orderItem.getQuantityToOrder();
//
//            BigDecimal discount = orderItemEventPrice
//                    .multiply(orderItemEventDiscountPercent)
//                    .divide(BigDecimal.valueOf(100));
//            BigDecimal finalItemPrice = orderItemEventPrice.subtract(discount);
//            BigDecimal totalPrice = finalItemPrice.multiply(BigDecimal.valueOf(quantityToOrder));
//
//            OrderItemEvent orderItemEvent = new OrderItemEvent(
//                    orderItem.getProductId(),
//                    quantityToOrder,
//                    orderItemEventPrice,
//                    orderItemEventDiscountPercent,
//                    totalPrice
//            );

        return null;
    }
}
