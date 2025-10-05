package com.soulware.tcompro.sharedkernel.policies.infrastructure.persistence.jpa.repositories;

import com.soulware.tcompro.sharedkernel.policies.domain.model.entities.PickupMethod;
import com.soulware.tcompro.sharedkernel.policies.domain.model.valueobjects.PickupMethods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PickupMethodRepository extends JpaRepository<PickupMethod, Long> {
    Optional<PickupMethod> findByType(PickupMethods type);
}
