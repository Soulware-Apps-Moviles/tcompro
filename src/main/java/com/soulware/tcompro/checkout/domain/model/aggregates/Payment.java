package com.soulware.tcompro.checkout.domain.model.aggregates;

import com.soulware.tcompro.checkout.domain.model.valueobjects.DebtId;
import com.soulware.tcompro.checkout.domain.model.valueobjects.PaymentId;
import com.soulware.tcompro.shared.domain.model.valueobjects.Money;
import com.soulware.tcompro.shared.domain.model.valueobjects.OrderId;
import com.soulware.tcompro.shared.domain.model.valueobjects.ShopId;
import com.soulware.tcompro.sharedkernel.customer.domain.model.valueobjects.CustomerId;
import com.soulware.tcompro.sharedkernel.policies.domain.model.valueobjects.PaymentMethodId;
import jakarta.persistence.*;
import jakarta.validation.constraints.Null;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "payments")
@Setter
@Getter
public class Payment {

    @EmbeddedId
    private PaymentId id;

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

    @Embedded
    @AttributeOverride(
            name = "value",
            column = @Column(name = "payment_method_id", nullable = false, updatable = false)
    )
    private PaymentMethodId  paymentMethodId;

    @Embedded
    @AttributeOverride(
            name = "value",
            column = @Column(name = "debt_id")
    )
    @Null
    private DebtId debtId;

    @Embedded
    @AttributeOverride(
            name = "value",
            column = @Column(name = "shop_id", nullable = false, updatable = false)
    )
    private ShopId shopId;

    protected Payment() {}

    public Payment(PaymentId id, CustomerId customerId, OrderId orderId, Money amount,  PaymentMethodId paymentMethodId,ShopId shopId) {
        this.id = id;
        this.customerId = customerId;
        this.orderId = orderId;
        this.amount = amount;
        this.paymentMethodId = paymentMethodId;
        this.shopId = shopId;
    }
}
