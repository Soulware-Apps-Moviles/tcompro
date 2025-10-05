package com.soulware.tcompro.checkout.domain.model.valueobjects;

import com.soulware.tcompro.shared.domain.model.valueobjects.BaseId;
import jakarta.persistence.Embeddable;

@Embeddable
public class DebtStatusId extends BaseId {
    public DebtStatusId(){}
    public DebtStatusId(Long value){super(value);}
}
