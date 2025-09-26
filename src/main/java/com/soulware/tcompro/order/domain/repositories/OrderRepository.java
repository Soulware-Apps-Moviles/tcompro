package com.soulware.tcompro.order.domain.repositories;

import com.soulware.tcompro.order.domain.model.aggregates.Order;
import com.soulware.tcompro.shared.domain.model.valueobjects.OrderId;
import com.soulware.tcompro.shared.domain.model.valueobjects.ShopId;
import com.soulware.tcompro.sharedkernel.customer.domain.model.valueobjects.CustomerId;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    Optional<Order> findById(OrderId orderId);
    Order save(Order order);
    Optional<List<Order>> findByCustomerId(CustomerId customerId);
    Optional<List<Order>> findByShopId(ShopId shopId);
}
