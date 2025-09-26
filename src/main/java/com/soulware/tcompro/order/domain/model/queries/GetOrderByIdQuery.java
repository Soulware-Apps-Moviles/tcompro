package com.soulware.tcompro.order.domain.model.queries;

import com.soulware.tcompro.shared.domain.model.valueobjects.OrderId;

public record GetOrderByIdQuery(OrderId orderId) {
}
