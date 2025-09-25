package com.soulware.tcompro.sharedkernel.domain.model.valueobjects;

import jakarta.persistence.*;

import java.util.regex.Pattern;

@Embeddable
public record PhoneNumber(@Column(name = "phone_number") String value) {

    private static final Pattern E164_PATTERN = Pattern.compile("^\\+[1-9]\\d{1,14}$");

    public PhoneNumber {
        if (value == null || !E164_PATTERN.matcher(value).matches()) {
            throw new IllegalArgumentException();
        }
    }
}