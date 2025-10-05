package com.soulware.tcompro.iam.domain.model.valueobjects;

import com.soulware.tcompro.shared.domain.model.valueobjects.BaseId;
import jakarta.persistence.Embeddable;

@Embeddable
public class ProfileId extends BaseId {
    public ProfileId() {
    }
    public ProfileId(Long value)  {
        super(value);
    }
}
