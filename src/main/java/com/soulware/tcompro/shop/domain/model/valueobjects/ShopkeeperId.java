package com.soulware.tcompro.shop.domain.model.valueobjects;

import com.soulware.tcompro.shared.domain.model.valueobjects.BaseId;
import jakarta.persistence.Embeddable;

@Embeddable
public class ShopkeeperId extends BaseId {
    public ShopkeeperId() {}
    public ShopkeeperId(Long value) {super(value);}
}
