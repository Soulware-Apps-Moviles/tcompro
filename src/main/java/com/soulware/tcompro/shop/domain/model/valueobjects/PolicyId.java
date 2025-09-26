package com.soulware.tcompro.shop.domain.model.valueobjects;

import com.soulware.tcompro.shared.domain.model.valueobjects.BaseId;
import jakarta.persistence.Embeddable;

@Embeddable
public class PolicyId extends BaseId {
    public PolicyId() {}
    public PolicyId(Long value){super(value);}

}
