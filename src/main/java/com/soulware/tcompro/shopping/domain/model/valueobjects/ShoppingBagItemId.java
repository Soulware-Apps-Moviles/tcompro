package com.soulware.tcompro.shopping.domain.model.valueobjects;

import com.soulware.tcompro.shared.domain.model.valueobjects.BaseId;
import jakarta.persistence.Embeddable;

@Embeddable
public class ShoppingBagItemId extends BaseId {
    public ShoppingBagItemId() {}
    public ShoppingBagItemId(Long value){super(value);}
}
