package com.soulware.tcompro.order.infrastructure.persistence.jpa.repositories;

import com.soulware.tcompro.order.domain.model.aggregates.Order;
import com.soulware.tcompro.shared.domain.model.valueobjects.OrderId;
import com.soulware.tcompro.shared.domain.model.valueobjects.ShopId;
import com.soulware.tcompro.sharedkernel.customer.domain.model.valueobjects.CustomerId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    Optional<Order> findByOrderId(OrderId id);

    @Query(value = """
        SELECT o.* 
        FROM orders o
        INNER JOIN order_statuses s ON o.status_id = s.id
        WHERE (:customerId IS NULL OR o.customer_id = :customerId)
          AND (:shopId IS NULL OR o.shop_id = :shopId)
          AND (:status IS NULL OR s.name = :status)
        """, nativeQuery = true)
    List<Order> findOrders(
            @Param("customerId") Long customerId,
            @Param("shopId") Long shopId,
            @Param("status") String status
    );
}
