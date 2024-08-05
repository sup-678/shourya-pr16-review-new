package com.ataccama.hiring.stats;

import java.time.LocalDateTime;

public class main {
    public static void main(String[] args) {
        Order order = new Order(1);
        order.setFinalized(false);
        order.setFinalizedAt(LocalDateTime.MAX);

        order.setFinalized(true);
        System.out.println(order);
    }
}
