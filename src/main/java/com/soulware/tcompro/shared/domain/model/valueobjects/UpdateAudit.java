package com.soulware.tcompro.shared.domain.model.valueobjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@MappedSuperclass
@Embeddable
public class UpdateAudit {
    @LastModifiedDate
    @Column(nullable = false)
    private Date updatedAt;
}
