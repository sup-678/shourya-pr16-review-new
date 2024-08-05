package com.ataccama.hiring.order;

import com.ataccama.hiring.stats.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private RabbitMqService rabbitMqService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Order getOrder(int id) {
        return jdbcTemplate.queryForObject("...", Order.class); // NOTE: query and mapping irrelevant
    }

    @Override
    @Transactional
    public void finalizeOrder(int orderId) {
        Order order = getOrder(orderId);
        rabbitMqService.send(new FinalizeOrder(order));
        order.setFinalized(true);
        order.setFinalizedAt(LocalDateTime.now());
        saveOrder(order);
    }

    private void saveOrder(Order order) {
        // NOTE: save using jdbcTemplate otherwise irrelevant
    }
}
