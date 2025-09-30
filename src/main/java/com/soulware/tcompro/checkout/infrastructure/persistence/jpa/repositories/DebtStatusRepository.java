package com.soulware.tcompro.checkout.infrastructure.persistence.jpa.repositories;

import com.soulware.tcompro.checkout.domain.model.entities.DebtStatus;
import com.soulware.tcompro.checkout.domain.model.valueobjects.DebtStatuses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DebtStatusRepository extends JpaRepository<DebtStatus, Long> {
    Optional<DebtStatus> findByName(DebtStatuses name);
}
