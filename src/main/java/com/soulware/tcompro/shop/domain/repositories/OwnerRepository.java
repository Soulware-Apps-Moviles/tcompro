package com.soulware.tcompro.shop.domain.repositories;

import com.soulware.tcompro.shop.domain.model.aggregates.Owner;
import com.soulware.tcompro.shop.domain.model.valueobjects.OwnerId;

import java.util.Optional;

public interface OwnerRepository {
    Optional<Owner> findById(OwnerId id);
    Owner save(Owner owner);
}
