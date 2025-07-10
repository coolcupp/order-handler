package com.coolcupp.common_lib.event;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class KafkaCreateOrderEvent {

    private UUID orderId;

    private Long userId;

    private List<KafkaCreateOrderEventItem> eventItemList;

    public KafkaCreateOrderEvent() {
    }

    public KafkaCreateOrderEvent(UUID orderId, Long userId, List<KafkaCreateOrderEventItem> eventItemList) {
        this.orderId = orderId;
        this.userId = userId;
        this.eventItemList = eventItemList;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<KafkaCreateOrderEventItem> getEventItemList() {
        return eventItemList;
    }

    public void setEventItemList(List<KafkaCreateOrderEventItem> eventItemList) {
        this.eventItemList = eventItemList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KafkaCreateOrderEvent that = (KafkaCreateOrderEvent) o;
        return Objects.equals(orderId, that.orderId) && Objects.equals(userId, that.userId) && Objects.equals(eventItemList, that.eventItemList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, userId, eventItemList);
    }

    @Override
    public String toString() {
        return "KafkaCreateOrderEvent{" +
                "orderId=" + orderId +
                ", userId=" + userId +
                ", eventItemList=" + eventItemList +
                '}';
    }
}
