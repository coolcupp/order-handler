package com.coolcupp.common_lib.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KafkaCreateOrderEvent {
    private UUID orderId;
    private Long userId;
    private List<KafkaCreateOrderEventItem> eventItemList;
}
