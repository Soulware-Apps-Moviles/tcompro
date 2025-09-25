package com.soulware.tcompro.shared.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public class PaymentMethodId extends BaseId{
    public PaymentMethodId() {}
    public PaymentMethodId(Long value) { super(value); }
}
