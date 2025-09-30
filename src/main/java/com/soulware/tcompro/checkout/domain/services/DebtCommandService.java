package com.soulware.tcompro.checkout.domain.services;

import com.soulware.tcompro.checkout.domain.model.aggregates.Debt;
import com.soulware.tcompro.checkout.domain.model.commands.MarkDebtAsPaidCommand;
import com.soulware.tcompro.checkout.domain.model.commands.RegisterDebtCommand;

import java.util.Optional;

public interface DebtCommandService {
    Optional<Debt> handle(RegisterDebtCommand command);
    Optional<Debt> handle(MarkDebtAsPaidCommand command);
}
