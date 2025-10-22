package com.soulware.tcompro.shop.infrastructure.persistence.jpa.repositories;

import com.soulware.tcompro.shared.domain.model.valueobjects.ShopId;
import com.soulware.tcompro.sharedkernel.customer.domain.model.valueobjects.CustomerId;
import com.soulware.tcompro.shop.domain.model.aggregates.TrustedCustomer;
import com.soulware.tcompro.shop.domain.model.valueobjects.TrustedCustomerId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrustedCustomerRepository extends JpaRepository<TrustedCustomer, Long> {
    Optional<TrustedCustomer> findById(TrustedCustomerId id);
    List<TrustedCustomer> findAllByCustomerId(CustomerId customerId);
    List<TrustedCustomer> findAllByShopId(ShopId shopId);
}
