package com.soulware.tcompro.iam.domain.model.aggregates;

import com.soulware.tcompro.iam.domain.model.valueobjects.AuthId;
import com.soulware.tcompro.iam.domain.model.valueobjects.ProfileId;
import com.soulware.tcompro.shared.domain.model.valueobjects.*;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;


@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Getter
public abstract class Profile {

    @Embedded
    private AuthId authId;

    @Embedded
    private PersonName name;

    @Embedded
    private EmailAddress email;

    @Embedded
    @Nullable
    private PhoneNumber phone;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Date createdAt;
    @LastModifiedDate
    @Column(nullable = false)
    private Date updatedAt;

    public Profile(AuthId authId, PersonName name, EmailAddress email, @Nullable PhoneNumber phone) {
        this.authId = authId;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }


    protected Profile() {}
}
