package com.soulware.tcompro.sharedkernel.policies.domain.model.entities;

import com.soulware.tcompro.sharedkernel.policies.domain.model.valueobjects.PaymentMethodId;
import com.soulware.tcompro.sharedkernel.policies.domain.model.valueobjects.PaymentMethods;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentMethod {

    @EmbeddedId
    private PaymentMethodId id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private PaymentMethods name;

}
