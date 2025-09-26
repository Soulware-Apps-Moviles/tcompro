package com.soulware.tcompro.inventory.domain.services;

import com.soulware.tcompro.inventory.domain.model.valueobjects.ProductId;
import com.soulware.tcompro.shared.domain.model.valueobjects.Money;

public interface SpecialOfferService {
    void applyDiscount(ProductId productId, Money price);
}
