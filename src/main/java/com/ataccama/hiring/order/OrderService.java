package com.ataccama.hiring.order;

import com.ataccama.hiring.stats.Order;

public interface OrderService {

    Order getOrder(int id);

    void finalizeOrder(int orderId);
}
