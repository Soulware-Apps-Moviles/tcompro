package com.soulware.tcompro.shop.interfaces.rest.resources;

import java.math.BigDecimal;

public record UpdateCreditLimitResource(Long customerId, BigDecimal creditLimit) {
}
