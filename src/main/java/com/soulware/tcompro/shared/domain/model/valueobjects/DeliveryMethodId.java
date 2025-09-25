package com.soulware.tcompro.shared.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public class DeliveryMethodId extends BaseId{
    public DeliveryMethodId() {}
    public DeliveryMethodId(Long value) { super(value); }
}
