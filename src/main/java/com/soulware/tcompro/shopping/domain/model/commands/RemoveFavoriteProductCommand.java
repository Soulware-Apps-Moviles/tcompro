package com.soulware.tcompro.shopping.domain.model.commands;

import com.soulware.tcompro.sharedkernel.customer.domain.model.valueobjects.CustomerId;
import com.soulware.tcompro.shopping.domain.model.valueobjects.FavoriteProductId;

public record RemoveFavoriteProductCommand(FavoriteProductId favoriteProductId) {
}
