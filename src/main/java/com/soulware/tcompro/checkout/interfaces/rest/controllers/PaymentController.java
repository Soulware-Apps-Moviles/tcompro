package com.soulware.tcompro.checkout.interfaces.rest.controllers;

import com.soulware.tcompro.checkout.domain.model.aggregates.Payment;
import com.soulware.tcompro.checkout.domain.model.queries.GetPaymentsQuery;
import com.soulware.tcompro.checkout.domain.services.PaymentQueryService;
import com.soulware.tcompro.checkout.interfaces.rest.assemblers.PaymentResourceFromEntityAssembler;
import com.soulware.tcompro.checkout.interfaces.rest.resources.PaymentResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/payments/v1", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Payments", description = "Endpoints for Payments")
public class PaymentController {
    private final PaymentQueryService  paymentQueryService;

    PaymentController(PaymentQueryService paymentQueryService) {
        this.paymentQueryService = paymentQueryService;
    }

    //Create Payment is called by an event, not by an endpoint

    @GetMapping
    ResponseEntity<List<PaymentResource>> getPayments(
            @RequestParam(required = false) Long customerId,
            @RequestParam(required = false) Long shopId){
        var query = new GetPaymentsQuery(customerId, shopId);
        List<Payment> payments = paymentQueryService
                .handle(query);
        List<PaymentResource> resources = payments.stream()
                .map(PaymentResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }
}
