package com.soulware.tcompro.sharedkernel.customer.domain.model.aggregates;

import com.soulware.tcompro.iam.domain.model.aggregates.Profile;
import com.soulware.tcompro.iam.domain.model.valueobjects.ProfileId;
import com.soulware.tcompro.sharedkernel.customer.domain.model.valueobjects.CustomerId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "customers")
public class Customer extends Profile {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EmbeddedId
    private CustomerId id;

    @Embedded
    @AttributeOverride(
            name = "value",
            column = @Column(name = "profile_id", nullable = false)
    )
    private ProfileId profileId;

}
