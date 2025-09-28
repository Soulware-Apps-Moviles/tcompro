package com.soulware.tcompro.shop.application.queryservices;

import com.soulware.tcompro.shared.domain.model.valueobjects.ShopId;
import com.soulware.tcompro.sharedkernel.customer.domain.model.valueobjects.CustomerId;
import com.soulware.tcompro.shop.domain.model.aggregates.TrustedCustomer;
import com.soulware.tcompro.shop.domain.model.queries.GetAllTrustedCustomersByShopIdQuery;
import com.soulware.tcompro.shop.domain.model.queries.GetTrustedCustomerByCustomerIdQuery;
import com.soulware.tcompro.shop.domain.services.TrustedCustomerQueryService;
import com.soulware.tcompro.shop.infrastructure.persistence.jpa.repositories.TrustedCustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrustedCustomerQueryServiceImpl implements TrustedCustomerQueryService {
    private final TrustedCustomerRepository trustedCustomerRepository;

    public  TrustedCustomerQueryServiceImpl(TrustedCustomerRepository trustedCustomerRepository) {
        this.trustedCustomerRepository = trustedCustomerRepository;
    }

    @Override
    public Optional<List<TrustedCustomer>> handle(GetTrustedCustomerByCustomerIdQuery query){
        return trustedCustomerRepository.findAllByCustomerId(new CustomerId(query.customerId()));
    }

    @Override
    public Optional<List<TrustedCustomer>> handle(GetAllTrustedCustomersByShopIdQuery query){
        return trustedCustomerRepository.findAllByShopId(new ShopId(query.shopId()));
    }

}
