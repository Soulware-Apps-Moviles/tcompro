package com.soulware.tcompro.shop.domain.services;

import com.soulware.tcompro.shop.domain.model.aggregates.TrustedCustomer;
import com.soulware.tcompro.shop.domain.model.queries.GetAllTrustedCustomersByShopIdQuery;
import com.soulware.tcompro.shop.domain.model.queries.GetTrustedCustomerByIdQuery;

import java.util.List;
import java.util.Optional;

public interface TrustedQueryService {
    Optional<TrustedCustomer>  handle(GetTrustedCustomerByIdQuery query);
    Optional<List<TrustedCustomer>> handle(GetAllTrustedCustomersByShopIdQuery query);
}
