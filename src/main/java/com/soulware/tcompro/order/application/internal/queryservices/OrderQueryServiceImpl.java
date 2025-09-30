package com.soulware.tcompro.order.application.internal.queryservices;

import com.soulware.tcompro.order.domain.model.aggregates.Order;
import com.soulware.tcompro.order.domain.model.queries.GetAllOrdersQuery;
import com.soulware.tcompro.order.domain.model.queries.GetOrderByIdQuery;
import com.soulware.tcompro.order.domain.services.OrderQueryService;
import com.soulware.tcompro.order.infrastructure.persistence.jpa.repositories.OrderRepository;
import com.soulware.tcompro.shared.domain.model.valueobjects.OrderId;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderQueryServiceImpl implements OrderQueryService {
    private final OrderRepository orderRepository;

    public  OrderQueryServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Optional<Order> handle(GetOrderByIdQuery query){
        return orderRepository.findByOrderId(new OrderId(query.orderId()));
    }

    @Override
    public List<Order> handle(GetAllOrdersQuery query){
        return orderRepository.findOrders(query.customerId(),query.shopId(),query.status());
    }
}
