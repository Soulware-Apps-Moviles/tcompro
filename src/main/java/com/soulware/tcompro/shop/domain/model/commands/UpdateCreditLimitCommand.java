package com.soulware.tcompro.shop.domain.model.commands;

import jakarta.validation.constraints.Null;

import java.math.BigDecimal;

public record UpdateCreditLimitCommand(Long trustedCustomerId, BigDecimal creditLimit) {
}
