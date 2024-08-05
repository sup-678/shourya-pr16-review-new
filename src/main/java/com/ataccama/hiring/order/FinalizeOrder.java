package com.ataccama.hiring.order;

import com.ataccama.hiring.stats.Order;

/*
NOTE:
    Its irrelevant what happens to the message inside the rabbit service.
 */
public class FinalizeOrder implements RabbitMessage {
    private Order order;

    public FinalizeOrder(Order order) {
        this.order = order;
    }
}
