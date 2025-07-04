package com.coolcupp.notification_service.mapper;

import com.coolcupp.notification_service.dto.OrderResponseDTO;
import com.coolcupp.notification_service.model.Order;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderResponseDTO toOrderResponseDTO(Order order);

    List<OrderResponseDTO> toOrderResponseDTOList(List<Order> orders);

}
