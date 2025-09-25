package com.soulware.tcompro.sharedkernel.domain.model.aggregates;

import com.soulware.tcompro.shared.domain.model.valueobjects.CreateAudit;
import com.soulware.tcompro.sharedkernel.domain.model.valueobjects.CustomerId;
import com.soulware.tcompro.shared.domain.model.valueobjects.UpdateAudit;
import com.soulware.tcompro.sharedkernel.domain.model.valueobjects.EmailAddress;
import com.soulware.tcompro.sharedkernel.domain.model.valueobjects.PersonName;
import com.soulware.tcompro.sharedkernel.domain.model.valueobjects.PhoneNumber;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "customers")
public class Customer {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EmbeddedId
    private CustomerId id;

    @Embedded
    private PersonName name;

    @Embedded
    private EmailAddress email;

    @Embedded
    private PhoneNumber phone;

    @Embedded
    private UpdateAudit updateAt;

    @Embedded
    private CreateAudit  createAt;

}
