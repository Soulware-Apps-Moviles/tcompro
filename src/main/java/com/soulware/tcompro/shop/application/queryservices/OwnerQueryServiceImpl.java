package com.soulware.tcompro.shop.application.queryservices;

import com.soulware.tcompro.shared.domain.model.valueobjects.EmailAddress;
import com.soulware.tcompro.shop.domain.model.aggregates.Owner;
import com.soulware.tcompro.shop.domain.model.queries.GetOwnerByEmailAddressQuery;
import com.soulware.tcompro.shop.domain.services.OwnerQueryService;
import com.soulware.tcompro.shop.infrastructure.persistence.jpa.repositories.OwnerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OwnerQueryServiceImpl implements OwnerQueryService {
    private final OwnerRepository ownerRepository;

    public OwnerQueryServiceImpl(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    public Optional<Owner> handle(GetOwnerByEmailAddressQuery query){
        return Optional.ofNullable(ownerRepository.findByEmail(new EmailAddress(query.email()))
                .orElseThrow(() -> new IllegalArgumentException(
                        "Owner with email " + query.email() + " does not exists"
                )));
    }
}
