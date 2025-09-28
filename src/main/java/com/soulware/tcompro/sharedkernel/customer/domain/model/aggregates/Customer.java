package com.soulware.tcompro.sharedkernel.customer.domain.model.aggregates;

import com.soulware.tcompro.iam.domain.model.aggregates.Profile;
import com.soulware.tcompro.iam.domain.model.valueobjects.AuthId;
import com.soulware.tcompro.iam.domain.model.valueobjects.ProfileId;
import com.soulware.tcompro.shared.domain.model.valueobjects.EmailAddress;
import com.soulware.tcompro.shared.domain.model.valueobjects.PersonName;
import com.soulware.tcompro.shared.domain.model.valueobjects.PhoneNumber;
import com.soulware.tcompro.sharedkernel.customer.domain.model.valueobjects.CustomerId;
import jakarta.annotation.Nullable;
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

    protected Customer() {}

    public Customer(CustomerId customerId, AuthId authId, PersonName personName, EmailAddress email, @Nullable PhoneNumber phone) {
        super(authId, personName, email, phone);
        this.id = customerId;
    }

}
