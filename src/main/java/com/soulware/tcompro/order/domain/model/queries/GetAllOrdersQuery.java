package com.soulware.tcompro.order.domain.model.queries;

import jakarta.validation.constraints.Null;

public record GetAllOrdersQuery(@Null Long shopId, @Null Long customerId, @Null String status) {
}
