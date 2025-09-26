package com.soulware.tcompro.sharedkernel.customer.domain.model.valueobjects;

import com.soulware.tcompro.shared.domain.model.valueobjects.BaseId;
import jakarta.persistence.Embeddable;

@Embeddable
public final class CustomerId extends BaseId {
    public CustomerId() {}
    public CustomerId(Long value) { super(value); }
}
