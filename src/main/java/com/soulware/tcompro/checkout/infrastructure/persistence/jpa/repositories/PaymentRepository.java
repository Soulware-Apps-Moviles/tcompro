package com.soulware.tcompro.checkout.infrastructure.persistence.jpa.repositories;

import com.soulware.tcompro.checkout.domain.model.aggregates.Payment;
import com.soulware.tcompro.checkout.domain.model.valueobjects.PaymentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findById(PaymentId id);

    @Query("""
    SELECT p FROM Payment p
    WHERE (:customerId IS NULL OR p.customerId.value = :customerId)
      AND (:shopId IS NULL OR p.shopId.value = :shopId)
""")
    List<Payment> findPaymentsOp(
            @Param("customerId") Long customerId,
            @Param("shopId") Long shopId
    );
}
