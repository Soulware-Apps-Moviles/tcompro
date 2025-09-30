package com.soulware.tcompro.shopping.domain.services;

import com.soulware.tcompro.shopping.domain.model.aggregates.FavoriteProduct;
import com.soulware.tcompro.shopping.domain.model.commands.AddFavoriteProductCommand;
import com.soulware.tcompro.shopping.domain.model.commands.RemoveFavoriteProductCommand;

import java.util.Optional;

public interface FavoriteProductCommandService {
    Optional<FavoriteProduct> handle(AddFavoriteProductCommand command);
    Optional<Long> handle(RemoveFavoriteProductCommand command);
}
