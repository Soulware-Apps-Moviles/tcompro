package com.soulware.tcompro.shared.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public class OrderId extends BaseId{
    public OrderId(){
    }
    public OrderId(Long orderId){super(orderId);}
}
