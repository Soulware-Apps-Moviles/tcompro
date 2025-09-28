package com.soulware.tcompro.shop.domain.services;

import com.soulware.tcompro.shop.domain.model.aggregates.Owner;
import com.soulware.tcompro.shop.domain.model.queries.GetOwnerByEmailAddressQuery;

import java.util.Optional;

public interface OwnerQueryService {
    Optional<Owner>  handle(GetOwnerByEmailAddressQuery query);
}
