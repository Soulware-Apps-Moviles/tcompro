package com.soulware.tcompro.shop.domain.model.aggregates;

import com.soulware.tcompro.iam.domain.model.aggregates.Profile;
import com.soulware.tcompro.iam.domain.model.valueobjects.ProfileId;
import com.soulware.tcompro.shop.domain.model.valueobjects.OwnerId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "owners")
@Setter
@Getter
public class Owner extends Profile {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EmbeddedId
    private OwnerId id;

    @Embedded
    @AttributeOverride(
            name = "value",
            column = @Column(name = "profile_id", nullable = false)
    )
    private ProfileId profileId;
}
