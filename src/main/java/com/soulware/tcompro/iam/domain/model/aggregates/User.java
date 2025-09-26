package com.soulware.tcompro.iam.domain.model.aggregates;

import com.soulware.tcompro.iam.domain.model.entities.Role;
import com.soulware.tcompro.iam.domain.model.valueobjects.Password;
import com.soulware.tcompro.iam.domain.model.valueobjects.ProfileId;
import com.soulware.tcompro.shared.domain.model.valueobjects.UserId;
import com.soulware.tcompro.shared.domain.model.valueobjects.EmailAddress;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "users")
public class User {

    @EmbeddedId
    private UserId id;

    @Embedded
    private EmailAddress email;

    @Embedded
    private Password passwordHash;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "profile_id", nullable = false)
    private Profile profileId;

    protected User() {}

    public User(
            String email,
            String passwordHash,
            Role role
    ){
        this.email = new EmailAddress(email);
        this.passwordHash = new Password(passwordHash);
        this.role = role;
    }

    public void changePassword(Password password) {
        this.passwordHash = password;
    }

    public void changeEmail(EmailAddress email) {
        this.email = email;
    }

    public void assignRole(Role role) {
        this.role = role;
    }
}
