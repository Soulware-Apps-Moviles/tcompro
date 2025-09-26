package com.soulware.tcompro.checkout.domain.model.valueobjects;

import com.soulware.tcompro.shared.domain.model.valueobjects.BaseId;
import jakarta.persistence.Embeddable;

@Embeddable
public class DebtId extends BaseId {
    public DebtId() {
    }
    public DebtId(Long value){super(value);}
}
