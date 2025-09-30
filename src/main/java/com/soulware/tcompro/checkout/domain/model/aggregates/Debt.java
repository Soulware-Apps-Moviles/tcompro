package com.soulware.tcompro.checkout.domain.model.aggregates;

import com.soulware.tcompro.checkout.domain.model.entities.DebtStatus;
import com.soulware.tcompro.checkout.domain.model.valueobjects.DebtId;
import com.soulware.tcompro.checkout.domain.model.valueobjects.DebtStatuses;
import com.soulware.tcompro.shared.domain.model.valueobjects.Money;
import com.soulware.tcompro.shared.domain.model.valueobjects.OrderId;
import com.soulware.tcompro.shared.domain.model.valueobjects.ShopId;
import com.soulware.tcompro.sharedkernel.customer.domain.model.valueobjects.CustomerId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "debts")
@Setter
@Getter
public class Debt {

    @EmbeddedId
    private DebtId debtId;

    @Embedded
    @AttributeOverride(
            name = "value",
            column = @Column(name = "customer_id", nullable = false, updatable = false)
    )
    private CustomerId customerId;

    @Embedded
    @AttributeOverride(
            name = "value",
            column = @Column(name = "order_id", nullable = false, updatable = false)
    )
    private OrderId orderId;

    @Embedded
    private Money amount;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "status_id", nullable = false)
    private DebtStatus status;

    @Embedded
    @AttributeOverride(
            name = "value",
            column = @Column(name = "shop_id", nullable = false, updatable = false)
    )
    private ShopId shopId;

    protected Debt() {}

    public Debt(DebtId id, CustomerId customerId, OrderId orderId, Money amount, ShopId shopId, DebtStatus status) {
        this.debtId = id;
        this.customerId = customerId;
        this.orderId = orderId;
        this.amount = amount;
        this.shopId = shopId;
        this.status = status;
    }

    public void changeStatus(DebtStatus newStatus) {
        DebtStatuses current = this.status.getName();
        DebtStatuses target = newStatus.getName();

        if (!current.canTransitionTo(target)) {
            throw new IllegalStateException(
                    "Cannot transition from " + current + " to " + target
            );
        }

        this.status = newStatus;
    }

}
