package com.soulware.tcompro.order.infrastructure.persistence.jpa.repositories;

import com.soulware.tcompro.order.domain.model.entities.OrderStatus;
import com.soulware.tcompro.order.domain.model.valueobjects.OrderStatuses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderStatusRepository extends JpaRepository<OrderStatus,Long> {
    Optional<OrderStatus> findByName(OrderStatuses name);

}
