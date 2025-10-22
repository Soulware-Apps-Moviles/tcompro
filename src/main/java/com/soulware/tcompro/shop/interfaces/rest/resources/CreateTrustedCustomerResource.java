package com.soulware.tcompro.shop.interfaces.rest.resources;

import jakarta.validation.constraints.Null;

import java.math.BigDecimal;

public record CreateTrustedCustomerResource(String customerEmail, Long shopId, @Null BigDecimal creditLimit) {
}
