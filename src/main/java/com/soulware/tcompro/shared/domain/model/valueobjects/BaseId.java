package com.soulware.tcompro.shared.domain.model.valueobjects;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Column;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
@MappedSuperclass
public abstract class BaseId {

    @Column(name = "id", nullable = false)
    private Long value;

    protected BaseId() {}

    protected BaseId(Long value) {
        if (value == null || value < 1) {
            throw new IllegalArgumentException("Value must be >= 1");
        }
        this.value = value;
    }


    public Long getValue() {
        return value;
    }

}