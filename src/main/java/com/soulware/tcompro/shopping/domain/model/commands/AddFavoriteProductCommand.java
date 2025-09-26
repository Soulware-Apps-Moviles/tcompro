package com.soulware.tcompro.shopping.domain.model.commands;

import com.soulware.tcompro.shared.domain.model.valueobjects.CatalogProductId;
import com.soulware.tcompro.sharedkernel.customer.domain.model.valueobjects.CustomerId;

public record AddFavoriteProductCommand(CatalogProductId catalogProductId, CustomerId customerId) {
}
