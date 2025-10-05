package com.soulware.tcompro.checkout.infrastructure.persistence.jpa.repositories;

import com.soulware.tcompro.checkout.domain.model.aggregates.Debt;
import com.soulware.tcompro.checkout.domain.model.entities.DebtStatus;
import com.soulware.tcompro.checkout.domain.model.valueobjects.DebtId;
import com.soulware.tcompro.checkout.domain.model.valueobjects.DebtStatuses;
import com.soulware.tcompro.shared.domain.model.valueobjects.ShopId;
import com.soulware.tcompro.sharedkernel.customer.domain.model.valueobjects.CustomerId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DebtRepository extends JpaRepository<Debt,Long> {
    Optional<Debt> findByDebtId(DebtId debtId);

    @Query("""
    SELECT d FROM Debt d
    WHERE (:customerId IS NULL OR d.customerId.value = :customerId)
      AND (:shopId IS NULL OR d.shopId.value = :shopId)
      AND (:status IS NULL OR d.status.name = :status)
""")
    List<Debt> findDebtsOp(
            @Param("customerId") Long customerId,
            @Param("shopId") Long shopId,
            @Param("status") DebtStatuses status
    );
}
