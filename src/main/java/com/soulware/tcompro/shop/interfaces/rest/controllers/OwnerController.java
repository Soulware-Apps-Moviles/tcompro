package com.soulware.tcompro.shop.interfaces.rest.controllers;

import com.soulware.tcompro.shop.domain.model.aggregates.Owner;
import com.soulware.tcompro.shop.domain.model.queries.GetOwnerByEmailAddressQuery;
import com.soulware.tcompro.shop.domain.services.OwnerQueryService;
import com.soulware.tcompro.shop.interfaces.rest.assemblers.OwnerResourceFromEntityAssembler;
import com.soulware.tcompro.shop.interfaces.rest.resources.OwnerResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = "/owners/v1/", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Owners", description = "Endpoints for owners")
public class OwnerController {
    private final OwnerQueryService  ownerQueryService;

    OwnerController(OwnerQueryService ownerQueryService)
    {
        this.ownerQueryService = ownerQueryService;
    }

    @GetMapping()
    public ResponseEntity<OwnerResource> getOwnerByEmailAddress(@RequestParam String email){
        var query = new GetOwnerByEmailAddressQuery(email);
        Optional<Owner> owner = ownerQueryService
                .handle(query);
        return owner
                .map(source -> new ResponseEntity<>(OwnerResourceFromEntityAssembler.toResourceFromEntity(source), HttpStatus.OK))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
