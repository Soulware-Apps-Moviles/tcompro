package com.soulware.tcompro.shared.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public class UserId extends BaseId{
    public UserId(){}
    public UserId(Long value) { super(value); }
}
