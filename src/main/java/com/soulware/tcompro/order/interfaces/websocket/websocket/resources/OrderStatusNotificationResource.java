package com.soulware.tcompro.order.interfaces.websocket.websocket.resources;

public record OrderStatusNotificationResource(Long orderId, String status, Long shopId) {
}
