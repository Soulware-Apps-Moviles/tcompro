package com.soulware.tcompro.shop.domain.services;

import com.soulware.tcompro.shared.domain.model.valueobjects.Money;
import com.soulware.tcompro.shared.domain.model.valueobjects.ShopId;
import com.soulware.tcompro.sharedkernel.customer.domain.model.valueobjects.CustomerId;
import com.soulware.tcompro.shop.domain.model.aggregates.TrustedCustomer;

public interface CustomerTrustService {
    TrustedCustomer concedeTrust(CustomerId customerId, ShopId shopId, Money creditLimit);
    void removeTrust(TrustedCustomer trustedCustomer);
}
