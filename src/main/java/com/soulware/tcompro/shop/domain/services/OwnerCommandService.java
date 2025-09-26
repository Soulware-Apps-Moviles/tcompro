package com.soulware.tcompro.shop.domain.services;

import com.soulware.tcompro.shop.domain.model.aggregates.Owner;
import com.soulware.tcompro.shop.domain.model.commands.AddOwnerCommand;

import java.util.Optional;

public interface OwnerCommandService {
    Optional<Owner> handle(AddOwnerCommand command);
}
