package com.soulware.tcompro.shared.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public class ShopId extends BaseId{
    public ShopId() {}
    public ShopId(Long value) { super(value); }
}
