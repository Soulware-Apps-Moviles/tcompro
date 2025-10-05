package com.soulware.tcompro.inventory.domain.services;

import com.soulware.tcompro.inventory.domain.model.aggregates.Product;
import com.soulware.tcompro.inventory.domain.model.commands.AddProductCommand;
import com.soulware.tcompro.inventory.domain.model.commands.UpdateProductCommand;

import java.util.Optional;

public interface ProductCommandService {
    Optional<Product> handle(AddProductCommand command);

    Optional<Product> handle(UpdateProductCommand command);
}
