package com.soulware.tcompro.sharedkernel.policies.domain.model.valueobjects;

import com.soulware.tcompro.shared.domain.model.valueobjects.BaseId;
import jakarta.persistence.Embeddable;

@Embeddable
public class PickupMethodId extends BaseId {
    public PickupMethodId() {}
    public PickupMethodId(Long value) { super(value); }
}
