package com.soulware.tcompro.sharedkernel.policies.domain.model.entities;

import com.soulware.tcompro.sharedkernel.policies.domain.model.valueobjects.PickupMethodId;
import com.soulware.tcompro.sharedkernel.policies.domain.model.valueobjects.PickupMethods;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PickupMethod {

    @EmbeddedId
    private PickupMethodId id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private PickupMethods type;

    public String getStringName(){
        return type.toString();
    }
}
