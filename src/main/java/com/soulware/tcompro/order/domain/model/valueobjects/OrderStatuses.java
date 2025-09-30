package com.soulware.tcompro.order.domain.model.valueobjects;

import java.util.Set;

public enum OrderStatuses {
    PLACED {
        @Override
        public Set<OrderStatuses> nextStates() {
            return Set.of(ACCEPTED, REJECTED);
        }
    },
    ACCEPTED {
        @Override
        public Set<OrderStatuses> nextStates() {
            return Set.of(READY, CANCELED);
        }
    },
    READY {
        @Override
        public Set<OrderStatuses> nextStates() {
            return Set.of(PICKED_UP, DISPATCHED, CANCELED);
        }
    },
    DISPATCHED {
        @Override
        public Set<OrderStatuses> nextStates() {
            return Set.of(DELIVERED, CANCELED);
        }
    },
    PICKED_UP,
    DELIVERED,
    REJECTED,
    CANCELED;

    public Set<OrderStatuses> nextStates() {
        return Set.of();
    }

    public boolean canTransitionTo(OrderStatuses target) {
        return nextStates().contains(target);
    }
}
