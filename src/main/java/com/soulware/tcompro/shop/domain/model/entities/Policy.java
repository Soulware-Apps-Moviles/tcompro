package com.soulware.tcompro.shop.domain.model.entities;

import com.soulware.tcompro.shared.domain.model.valueobjects.Money;
import com.soulware.tcompro.sharedkernel.policies.domain.model.entities.PaymentMethod;
import com.soulware.tcompro.sharedkernel.policies.domain.model.entities.PickupMethod;
import com.soulware.tcompro.shop.domain.model.valueobjects.PolicyId;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "policies")
public class Policy {
    @EmbeddedId
    private PolicyId id;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<PaymentMethod>  paymentMethods = new ArrayList<PaymentMethod>();

    @ManyToMany(fetch = FetchType.EAGER)
    private List<PickupMethod> pickupMethods = new ArrayList<PickupMethod>();

    @Embedded
    private Money maxCreditPerCustomer;

    protected Policy() {}

    public Policy(
            List<PaymentMethod> paymentMethods,
            List<PickupMethod> pickupMethods,
            Money maxCreditPerCustomer
    ){
        this.paymentMethods = paymentMethods;
        this.pickupMethods = pickupMethods;
        this.maxCreditPerCustomer = maxCreditPerCustomer;
    }
}
