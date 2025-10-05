package com.soulware.tcompro.shared.domain.model.valueobjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@MappedSuperclass
@Embeddable
@EntityListeners(AuditingEntityListener.class)
public class UpdateAudit {
    @LastModifiedDate
    @Column(nullable = false)
    private Date updatedAt;
}
