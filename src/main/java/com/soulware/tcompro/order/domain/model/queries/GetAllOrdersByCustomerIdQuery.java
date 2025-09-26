package com.soulware.tcompro.order.domain.model.queries;

import com.soulware.tcompro.sharedkernel.customer.domain.model.valueobjects.CustomerId;

public record GetAllOrdersByCustomerIdQuery(CustomerId customerId) {
}
