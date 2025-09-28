package com.soulware.tcompro.shop.interfaces.rest.resources;

import jakarta.validation.constraints.Null;

import java.math.BigDecimal;

public record CreateTrustedCustomerResource(Long customerId, Long shopId, @Null BigDecimal creditLimit) {
}
