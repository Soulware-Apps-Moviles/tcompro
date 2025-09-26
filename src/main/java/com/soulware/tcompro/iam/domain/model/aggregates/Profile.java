package com.soulware.tcompro.iam.domain.model.aggregates;

import com.soulware.tcompro.iam.domain.model.valueobjects.ProfileId;
import com.soulware.tcompro.shared.domain.model.valueobjects.CreateAudit;
import com.soulware.tcompro.shared.domain.model.valueobjects.UpdateAudit;
import com.soulware.tcompro.shared.domain.model.valueobjects.EmailAddress;
import com.soulware.tcompro.shared.domain.model.valueobjects.PersonName;
import com.soulware.tcompro.shared.domain.model.valueobjects.PhoneNumber;
import jakarta.persistence.*;

@Entity
@Table(name = "profiles")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Profile {

    @EmbeddedId
    private ProfileId id;

    @Embedded
    private PersonName name;

    @Embedded
    private EmailAddress email;

    @Embedded
    private PhoneNumber phone;

    @Embedded
    private UpdateAudit updateAt;

    @Embedded
    private CreateAudit createAt;

}
