package com.soulware.tcompro.order.interfaces.rest.controllers;

import com.soulware.tcompro.order.domain.model.aggregates.Order;
import com.soulware.tcompro.order.domain.model.commands.AcceptOrderCommand;
import com.soulware.tcompro.order.domain.model.commands.AdvanceOrderCommand;
import com.soulware.tcompro.order.domain.model.commands.CancelOrderCommand;
import com.soulware.tcompro.order.domain.model.commands.RejectOrderCommand;
import com.soulware.tcompro.order.domain.model.queries.GetAllOrdersQuery;
import com.soulware.tcompro.order.domain.services.OrderCommandService;
import com.soulware.tcompro.order.domain.services.OrderQueryService;
import com.soulware.tcompro.order.interfaces.rest.assemblers.CreateOrderCommandFromResourceAssembler;
import com.soulware.tcompro.order.interfaces.rest.assemblers.OrderResourceFromEntityAssembler;
import com.soulware.tcompro.order.interfaces.rest.resources.CreateOrderResource;
import com.soulware.tcompro.order.interfaces.rest.resources.OrderResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/orders/v1", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Orders" , description = "Endpoints for order")
public class OrderController {
    private final OrderCommandService orderCommandService;
    private final OrderQueryService orderQueryService;

    OrderController(OrderCommandService orderCommandService, OrderQueryService orderQueryService) {
        this.orderCommandService = orderCommandService;
        this.orderQueryService = orderQueryService;
    }

    @PostMapping
    public ResponseEntity<OrderResource> createOrder(@RequestBody CreateOrderResource resource){
        Optional<Order> order = orderCommandService
                .handle(CreateOrderCommandFromResourceAssembler.toCommandFromResource(resource));
        return order
                .map(source -> new ResponseEntity<>(OrderResourceFromEntityAssembler.toResourceFromEntity(source), HttpStatus.CREATED))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @PostMapping("/{id}/accept")
    public ResponseEntity<OrderResource> acceptOrder(@PathVariable Long id){
        Optional<Order> order = orderCommandService
                .handle(new AcceptOrderCommand(id));
        return order
                .map(source -> new ResponseEntity<>(OrderResourceFromEntityAssembler.toResourceFromEntity(source), HttpStatus.CREATED))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @PostMapping("/{id}/reject")
    public ResponseEntity<OrderResource> rejectOrder(@PathVariable Long id){
        Optional<Order> order = orderCommandService
                .handle(new RejectOrderCommand(id));
        return order
                .map(source -> new ResponseEntity<>(OrderResourceFromEntityAssembler.toResourceFromEntity(source), HttpStatus.CREATED))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @PostMapping("/{id}/cancel")
    public ResponseEntity<OrderResource> cancelOrder(@PathVariable Long id){
        Optional<Order> order = orderCommandService
                .handle(new CancelOrderCommand(id));
        return order
                .map(source -> new ResponseEntity<>(OrderResourceFromEntityAssembler.toResourceFromEntity(source), HttpStatus.CREATED))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @PostMapping("/{id}/advance")
    public ResponseEntity<OrderResource> advanceOrder(@PathVariable Long id){
        Optional<Order> order = orderCommandService
                .handle(new AdvanceOrderCommand(id));
        return order
                .map(source -> new ResponseEntity<>(OrderResourceFromEntityAssembler.toResourceFromEntity(source), HttpStatus.CREATED))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @GetMapping
    public ResponseEntity<List<OrderResource>> getOrderById(
            @RequestParam(required = false) Long shopId,
            @RequestParam(required = false) Long customerId,
            @RequestParam(required = false) String status
    ){
        var query = new GetAllOrdersQuery(shopId, customerId, status);
        List<Order> orders = orderQueryService
                .handle(query);
        List<OrderResource> resources = orders.stream()
                .map(OrderResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }


}
