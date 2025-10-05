package com.soulware.tcompro.order.domain.services;

import com.soulware.tcompro.order.domain.model.aggregates.Order;
import com.soulware.tcompro.order.domain.model.queries.GetAllOrdersQuery;
import com.soulware.tcompro.order.domain.model.queries.GetOrderByIdQuery;

import java.util.List;
import java.util.Optional;

public interface OrderQueryService {
    Optional<Order> handle(GetOrderByIdQuery query);
    List<Order> handle(GetAllOrdersQuery query);
}
