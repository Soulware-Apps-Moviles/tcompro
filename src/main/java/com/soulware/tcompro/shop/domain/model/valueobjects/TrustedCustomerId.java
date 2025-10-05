package com.soulware.tcompro.shop.domain.model.valueobjects;

import com.soulware.tcompro.shared.domain.model.valueobjects.BaseId;
import jakarta.persistence.Embeddable;

@Embeddable
public class TrustedCustomerId extends BaseId {
    public TrustedCustomerId() {}
    public TrustedCustomerId(Long value){
        super(value);
    }
}
