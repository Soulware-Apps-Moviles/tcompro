package com.soulware.tcompro.sharedkernel.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Email;

@Embeddable
public record EmailAddress(@Email String email) {
    @Deprecated
    public EmailAddress(){
        this("johndoe@example.com");
    }

    public EmailAddress(@Email String email) {
        this.email = email;
    }
}
