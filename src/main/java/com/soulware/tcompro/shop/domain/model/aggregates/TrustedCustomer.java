package com.soulware.tcompro.shop.domain.model.aggregates;

import com.soulware.tcompro.shared.domain.model.valueobjects.Money;
import com.soulware.tcompro.shared.domain.model.valueobjects.ShopId;
import com.soulware.tcompro.sharedkernel.customer.domain.model.valueobjects.CustomerId;
import com.soulware.tcompro.shop.domain.model.valueobjects.TrustedCustomerId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "trusted_customers")
@Setter
@Getter
public class TrustedCustomer {
    @EmbeddedId
    private TrustedCustomerId id;

    @Embedded
    private Money creditLimit;

    @Embedded
    @AttributeOverride(
            name = "value",
            column = @Column(name = "customer_id",  nullable = false, updatable = false)
    )
    private CustomerId customerId;

    @Embedded
    @AttributeOverride(
            name = "value",
            column = @Column(name = "shop_id", nullable = false)
    )
    private ShopId shopId;

    private boolean isTrusted;

    protected TrustedCustomer(){}

    public TrustedCustomer(TrustedCustomerId id, Money creditLimit, CustomerId customerId) {
        this.id = id;
        this.creditLimit = creditLimit;
        this.customerId = customerId;
        this.isTrusted = true;
    }

    public void removeTrust(){
        this.isTrusted = false;
    }

    public void concedeTrust(){
        this.isTrusted = true;
    }
}
