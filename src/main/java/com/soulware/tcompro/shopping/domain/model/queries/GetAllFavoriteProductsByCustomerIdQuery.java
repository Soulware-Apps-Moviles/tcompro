package com.soulware.tcompro.shopping.domain.model.queries;

import com.soulware.tcompro.sharedkernel.customer.domain.model.valueobjects.CustomerId;

public record GetAllFavoriteProductsByCustomerIdQuery(CustomerId customerId) {
}
