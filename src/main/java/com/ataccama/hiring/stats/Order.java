package com.ataccama.hiring.stats;

import java.time.LocalDateTime;
import java.util.Objects;

public final class Order {

    public final int orderId;
    private boolean finalized;
    private LocalDateTime finalizedAt;

    public Order(int orderId) {
        this.orderId = orderId;
    }

    public void setFinalized(boolean finalized) {
        this.finalized = finalized;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return orderId == order.orderId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, finalized);
    }

    public void setFinalizedAt(LocalDateTime now) {
        this.finalizedAt = now;
    }

    public int getOrderId() {
        return orderId;
    }

    public boolean isFinalized() {
        return finalized;
    }

    public LocalDateTime getFinalizedAt() {
        return finalizedAt;
    }
}
