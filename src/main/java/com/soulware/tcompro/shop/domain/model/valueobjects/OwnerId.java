package com.soulware.tcompro.shop.domain.model.valueobjects;

import com.soulware.tcompro.shared.domain.model.valueobjects.BaseId;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EntityListeners;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@EntityListeners(AuditingEntityListener.class)
@Embeddable
public class OwnerId extends BaseId {
    public OwnerId() {}
    public OwnerId(Long value){super(value);}
}
