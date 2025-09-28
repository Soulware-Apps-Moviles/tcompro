package com.soulware.tcompro.shop.domain.model.aggregates;

import com.soulware.tcompro.iam.domain.model.aggregates.Profile;
import com.soulware.tcompro.iam.domain.model.valueobjects.AuthId;
import com.soulware.tcompro.iam.domain.model.valueobjects.ProfileId;
import com.soulware.tcompro.shared.domain.model.valueobjects.EmailAddress;
import com.soulware.tcompro.shared.domain.model.valueobjects.PersonName;
import com.soulware.tcompro.shared.domain.model.valueobjects.PhoneNumber;
import com.soulware.tcompro.shop.domain.model.valueobjects.OwnerId;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "owners")
@Setter
@Getter
public class Owner extends Profile {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EmbeddedId
    private OwnerId id;

    protected Owner() {}

    public Owner(OwnerId ownerId, AuthId authId, PersonName personName, EmailAddress email, @Nullable PhoneNumber phone) {
        super(authId, personName, email, phone);
        this.id = ownerId;
    }

}
