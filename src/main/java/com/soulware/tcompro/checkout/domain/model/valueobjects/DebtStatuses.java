package com.soulware.tcompro.checkout.domain.model.valueobjects;

public enum DebtStatuses {
    PENDING {
        @Override
        public boolean canTransitionTo(DebtStatuses target) {
            return target == PAID || target == UNRECOVERABLE;
        }
    },
    PAID {
        @Override
        public boolean canTransitionTo(DebtStatuses target) {
            return false; // estado final
        }
    },
    UNRECOVERABLE {
        @Override
        public boolean canTransitionTo(DebtStatuses target) {
            return false; // estado final
        }
    };

    public abstract boolean canTransitionTo(DebtStatuses target);
}
