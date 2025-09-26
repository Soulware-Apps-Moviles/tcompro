package com.soulware.tcompro.checkout.domain.model.entities;

import com.soulware.tcompro.checkout.domain.model.valueobjects.DebtStatusId;
import com.soulware.tcompro.checkout.domain.model.valueobjects.DebtStatuses;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "debt_statuses")
@NoArgsConstructor
@AllArgsConstructor
public class DebtStatus {

    @EmbeddedId
    private DebtStatusId id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private DebtStatuses name;
}
