package com.soulware.tcompro.order.domain.model.commands;

import com.soulware.tcompro.order.domain.model.entities.Orderline;
import com.soulware.tcompro.shared.domain.model.valueobjects.ShopId;
import com.soulware.tcompro.sharedkernel.customer.domain.model.valueobjects.CustomerId;

import java.util.List;

public record CreateOrderCommand(List<Orderline> orderlines, CustomerId customerId, ShopId shopId) {
}
