package com.soulware.tcompro.sharedkernel.policies.domain.model.valueobjects;

import com.soulware.tcompro.shared.domain.model.valueobjects.BaseId;
import jakarta.persistence.Embeddable;

@Embeddable
public class PaymentMethodId extends BaseId {
    public PaymentMethodId() {}
    public PaymentMethodId(Long value) { super(value); }
}
