package com.soulware.tcompro.order.domain.services;

import com.soulware.tcompro.order.domain.model.aggregates.Order;
import com.soulware.tcompro.order.domain.model.commands.*;

import java.util.Optional;

public interface OrderCommandService {
    Optional<Order> handle(CreateOrderCommand command);
    Optional<Order> handle(AcceptOrderCommand command);
    Optional<Order> handle(RejectOrderCommand command);
    Optional<Order> handle(CancelOrderCommand command);
    Optional<Order> handle(AdvanceOrderCommand command);
}
