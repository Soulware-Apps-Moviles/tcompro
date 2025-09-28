package com.soulware.tcompro.shop.infrastructure.persistence.jpa.repositories;

import com.soulware.tcompro.iam.domain.model.valueobjects.AuthId;
import com.soulware.tcompro.shared.domain.model.valueobjects.EmailAddress;
import com.soulware.tcompro.shop.domain.model.aggregates.Owner;
import com.soulware.tcompro.shop.domain.model.valueobjects.OwnerId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
    Optional<Owner> findByEmail(EmailAddress email);

    Optional<Owner> findByAuthId(AuthId id);

    Optional<Owner> findById(OwnerId id);
}
