package com.soulware.tcompro.sharedkernel.customer.infrastructure.persistence.jpa.repositories;

import com.soulware.tcompro.iam.domain.model.valueobjects.AuthId;
import com.soulware.tcompro.shared.domain.model.valueobjects.EmailAddress;
import com.soulware.tcompro.sharedkernel.customer.domain.model.aggregates.Customer;
import com.soulware.tcompro.sharedkernel.customer.domain.model.valueobjects.CustomerId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByAuthId(AuthId id);

    Optional<Customer> findByEmail(EmailAddress email);

    Optional<Customer> findById(CustomerId id);
}
