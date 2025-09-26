package com.soulware.tcompro.order.domain.model.commands;

import com.soulware.tcompro.shared.domain.model.valueobjects.OrderId;

public record CancelOrderCommand(OrderId orderId) {
}
