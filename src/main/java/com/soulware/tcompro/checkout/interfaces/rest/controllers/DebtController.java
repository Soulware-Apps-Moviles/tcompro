package com.soulware.tcompro.checkout.interfaces.rest.controllers;

import com.soulware.tcompro.checkout.domain.model.aggregates.Debt;
import com.soulware.tcompro.checkout.domain.model.commands.MarkDebtAsPaidCommand;
import com.soulware.tcompro.checkout.domain.model.queries.GetDebtsQuery;
import com.soulware.tcompro.checkout.domain.services.DebtCommandService;
import com.soulware.tcompro.checkout.domain.services.DebtQueryService;
import com.soulware.tcompro.checkout.interfaces.rest.assemblers.DebtResourceFromEntityAssembler;
import com.soulware.tcompro.checkout.interfaces.rest.resources.DebtResource;
import com.soulware.tcompro.checkout.interfaces.rest.resources.PaymentResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Null;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/debts/v1", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Debts", description = "Endpoints for debts")
public class DebtController {
    private DebtCommandService  debtCommandService;
    private DebtQueryService debtQueryService;

    DebtController(DebtCommandService debtCommandService, DebtQueryService debtQueryService) {
        this.debtCommandService = debtCommandService;
        this.debtQueryService = debtQueryService;
    }

    //Create Debt is called by an event, not by an endpoint

    @PostMapping("/{id}/paid")
    ResponseEntity<DebtResource> markDebtAsPaid(@PathVariable Long id){
        Optional<Debt> debt = debtCommandService
                .handle(new MarkDebtAsPaidCommand(id));
        return debt
                .map(source -> new ResponseEntity<>(DebtResourceFromEntityAssembler.toResourceFromEntity(source), HttpStatus.OK))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    ResponseEntity<List<DebtResource>> getDebts(
            @RequestParam(required = false) Long customerId,
            @RequestParam(required = false) Long shopId,
            @RequestParam(required = false) String status){
        var query = new GetDebtsQuery(customerId, shopId, status);
        List<Debt> debts = debtQueryService
                .handle(query);
        List<DebtResource> resources = debts.stream()
                .map(DebtResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }
}
