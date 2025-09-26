package com.soulware.tcompro.inventory.domain.services;

import com.soulware.tcompro.inventory.domain.model.aggregates.Product;
import com.soulware.tcompro.inventory.domain.model.commands.AddProductCommand;
import com.soulware.tcompro.inventory.domain.model.commands.ChangeProductPriceCommand;
import com.soulware.tcompro.inventory.domain.model.commands.RemoveProductCommand;

import java.util.Optional;

public interface ProductCommandService {
    Optional<Product> handle(AddProductCommand command);
    Optional<Product> handle(RemoveProductCommand command);
    Optional<Product> handle(ChangeProductPriceCommand command);
}
