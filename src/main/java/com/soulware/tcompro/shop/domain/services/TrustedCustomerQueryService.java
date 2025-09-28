package com.soulware.tcompro.shop.domain.services;

import com.soulware.tcompro.shop.domain.model.aggregates.TrustedCustomer;
import com.soulware.tcompro.shop.domain.model.queries.GetAllTrustedCustomersByShopIdQuery;
import com.soulware.tcompro.shop.domain.model.queries.GetTrustedCustomerByCustomerIdQuery;

import java.util.List;
import java.util.Optional;

public interface TrustedCustomerQueryService {
    Optional<List<TrustedCustomer>>  handle(GetTrustedCustomerByCustomerIdQuery query);
    Optional<List<TrustedCustomer>> handle(GetAllTrustedCustomersByShopIdQuery query);
}
